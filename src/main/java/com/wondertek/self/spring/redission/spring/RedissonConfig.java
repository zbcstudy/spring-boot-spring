package com.wondertek.self.spring.redission.spring;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author zbc
 * @Date 22:42-2019/1/29
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379").setConnectionPoolSize(100);
        return Redisson.create(config);
    }

    public RedissonConfig() {
        System.out.println("RedissonConfig{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                '}');
    }
}
