package com.wondertek.self.spring.redission.spring;

import org.junit.Test;
import org.redisson.RedissonClient;
import org.redisson.core.RAtomicLong;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zbc
 * @Date 23:05-2019/1/29
 */
public class RedisConfigTest {
    @Test
    public void testRedisson() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedissonConfig.class);
        RedissonClient redissonClient = applicationContext.getBean(RedissonClient.class);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                RAtomicLong atomicLong = redissonClient.getAtomicLong("spring:redis:lock");
                atomicLong.incrementAndGet();
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(redissonClient.getAtomicLong("spring:redis:lock").get());
    }
}
