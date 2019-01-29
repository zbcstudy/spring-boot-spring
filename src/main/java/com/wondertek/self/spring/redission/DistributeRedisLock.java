package com.wondertek.self.spring.redission;

import org.redisson.Redisson;
import org.redisson.core.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @Author zbc
 * @Date 21:29-2019/1/29
 */
public class DistributeRedisLock {

    private static Redisson redisson = RedissonManager.getRedisson();

    private static final String LOCK_NAME = "redisLock_";

    public static void tryAcquire(String name) {
        RLock rLock = getRLock(name);
        rLock.lock(1, TimeUnit.MINUTES);

    }

    public static void release(String name) {
        RLock rLock = getRLock(name);
        rLock.unlock();
    }

    private static RLock getRLock(String name) {
        String key = LOCK_NAME + name;
        RLock rLock = redisson.getLock(key);
        return rLock;
    }
}
