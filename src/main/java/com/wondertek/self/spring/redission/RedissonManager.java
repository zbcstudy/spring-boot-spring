package com.wondertek.self.spring.redission;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.core.RAtomicLong;

/**
 * 管理redisson的初始化和创建等操作
 * @Author zbc
 * @Date 21:16-2019/1/29
 */
public class RedissonManager {

    private static final String RAtomicName = "genId_";

    private static Config config = new Config();

    private static Redisson redisson = null;

    public static void init() {
        try {
            config.useSingleServer()
                    .setAddress("127.0.0.1:6379")
                    .setConnectionPoolSize(100)
                    .setConnectionMinimumIdleSize(10)
                    .setDatabase(0);
            redisson = (Redisson) Redisson.create(config);
            RAtomicLong atomicLong = redisson.getAtomicLong(RAtomicName);
            //清空自增的ID数字
            atomicLong.set(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Redisson getRedisson() {
        return redisson;
    }

    public static long nextId() {
        RAtomicLong atomicLong = getRedisson().getAtomicLong(RAtomicName);
        atomicLong.incrementAndGet();
        return atomicLong.get();
    }

}
