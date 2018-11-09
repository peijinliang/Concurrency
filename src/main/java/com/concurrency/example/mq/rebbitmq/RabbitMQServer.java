package com.concurrency.example.mq.rebbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Crete by Marlon
 * Create Date: 2018/6/17
 * Class Describe
 **/
@Component
@Slf4j
public class RabbitMQServer {

    @RabbitListener(queues = QueueConstans.TEST)
    private void receive(String message){
        log.info("{}",message);
    }

}
