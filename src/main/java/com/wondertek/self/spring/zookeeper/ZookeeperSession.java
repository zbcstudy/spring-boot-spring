package com.wondertek.self.spring.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author zbc
 * @Date 11:36-2019/1/1
 */
public class ZookeeperSession {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperSession.class);

    private static final int DEFAULT_TIMEOUT = 5000;
    private static final String DEFAULT_CONNECT = "127.0.0.1:2181";

    private static final String BASE_PATH = "/ad-lock-";

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    public ZookeeperSession() {
        try {
            this.zooKeeper = new ZooKeeper(DEFAULT_CONNECT, DEFAULT_TIMEOUT, new ZookeeperWatcher());
            //打印即时状态，验证是否异步
            log.info(String.valueOf(zooKeeper.getState()));

            // CountDownLatch：简而言之 初始化——非0；非0——等待；0——往下执行
            connectedSemaphore.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化实例
     */
    public static void init() {
        getInstance();
    }

    /**
     * 建立zk session的watcher
     */
    private class ZookeeperWatcher implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                connectedSemaphore.countDown();
            }
        }
    }

    /**
     * 静态内部类实现单例
     */
    private static class Singleton{
        private static ZookeeperSession zookeeperSession;

        static {
            zookeeperSession = new ZookeeperSession();
        }

        private static ZookeeperSession getInstance() {
            return zookeeperSession;
        }
    }

    /**
     * 从单例实现中获取实例
     * @return
     */
    public static ZookeeperSession getInstance() {
        return Singleton.getInstance();
    }


    /**
     * 重试获取分布式锁
     * @param adId
     */
    public void acquireDistributedLock(long adId) {
        String path = BASE_PATH + adId;

        try {
            zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            log.info("success to acquire lock for adId = " + adId);

        } catch (Exception e) {
            //adId对应的锁node已经存在，就是已经被别人加锁了，这里就会报错
            //NodeExistException
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(1000l);
                    zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e1) {
                    count++;
                    log.info("the {} times try to acquire lock for adId = {}", count, adId);
                }
                log.info("success to acquire lock for adId = " + adId + " after " + count + " times try......");
                break;
            }
        }
    }


    public void releaseDistributeLock(long adId) {
        String path = BASE_PATH + adId;
        try {
            zooKeeper.delete(path,-1);
            log.info("release the lock for adId = " + adId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Long adId = 1L;
        ZookeeperSession zkSession = ZookeeperSession.getInstance();
        //1、获取锁：
        zkSession.acquireDistributedLock(adId);

        //2、执行一些修改共享资源的操作
        log.info("I am updating common resource！");

        //3、释放锁
        zkSession.releaseDistributeLock(adId);
    }
}
