package com.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * Crete by Marlon
 * Create Date: 2018/6/17
 * Class Describe
 **/
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("jedis.host") String host,
                               @Value("jedis.port") int port) {
        return new JedisPool(host, port);
    }



}
