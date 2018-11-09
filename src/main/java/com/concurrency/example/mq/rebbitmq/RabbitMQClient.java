package com.concurrency.example.mq.rebbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * Crete by Marlon
 * Create Date: 2018/6/17
 * Class Describe
 **/

@Component
public class RabbitMQClient {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(QueueConstans.TEST, message);
    }

}
