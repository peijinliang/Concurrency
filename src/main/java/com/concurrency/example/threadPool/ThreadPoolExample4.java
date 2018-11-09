package com.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Crete by Marlon
 * Create Date: 2018/6/16
 * Class Describe
 **/

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

//        for (int i = 0; i < 10; i++) {
//            executorService.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    log.warn("schedule run");
//                }
//            }, 2, TimeUnit.SECONDS);
//        }

//        executorService.shutdown();

        //以指定的速率去执行数据   延迟1秒开始 ，每隔三秒执行一次
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        },1,3,TimeUnit.SECONDS);

//      executorService.scheduleWithFixedDelay() 以指定的延迟去执行任务

        //定时器 定时去执行某些任务
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000);


    }

}
