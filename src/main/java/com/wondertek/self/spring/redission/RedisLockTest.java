package com.wondertek.self.spring.redission;

/**
 * @Author zbc
 * @Date 21:36-2019/1/29
 */
public class RedisLockTest {
    public static void main(String[] args) {
        RedissonManager.init();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(()->{
                try {
                    String key = "test123";
                    DistributeRedisLock.tryAcquire(key);
                    Thread.sleep(500l);
                    System.out.println("==========获取所之后进行相应的操作");
                    DistributeRedisLock.release(key);
                    System.out.println("----------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread.start();
        }
    }
}
