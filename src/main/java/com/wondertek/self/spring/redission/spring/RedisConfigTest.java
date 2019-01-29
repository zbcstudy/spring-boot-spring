package com.wondertek.self.spring.redission.spring;

import org.junit.Test;
import org.redisson.RedissonClient;
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
        System.out.println(redissonClient);
    }
}
