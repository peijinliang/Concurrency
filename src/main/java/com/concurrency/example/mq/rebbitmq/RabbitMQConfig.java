package com.concurrency.example.mq.rebbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Crete by Marlon
 * Create Date: 2018/6/17
 * Class Describe
 **/

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue(){
       return  new Queue(QueueConstans.TEST);
    }


}
