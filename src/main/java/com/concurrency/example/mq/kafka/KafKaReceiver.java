package com.concurrency.example.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Crete by Marlon
 * Create Date: 2018/6/17
 * Class Describe
 **/

@Component
@Slf4j
public class KafKaReceiver {

    @KafkaListener(topics = {TopicConstans.TEST})
    public void receiver(ConsumerRecord<?, ?> record) {
        log.info("record:{}", record);
    }

}
