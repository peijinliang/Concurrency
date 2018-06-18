package com.concurrency.example.mq;

import com.concurrency.example.mq.kafka.KafkaSender;
import com.concurrency.example.mq.rebbitmq.RabbitMQClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * Crete by Marlon
 * Create Date: 2018/6/17
 * Class Describe
 **/
@Controller
@RequestMapping
public class MQController {

    @Resource
    private RabbitMQClient rabbitMQClient;

    @Resource
    private KafkaSender kafkaSender;

    @RequestMapping("/send")
    @ResponseBody
    private String send(@RequestParam("message") String message) {
        rabbitMQClient.send(message);
        kafkaSender.send(message);
        return "success";
    }

}
