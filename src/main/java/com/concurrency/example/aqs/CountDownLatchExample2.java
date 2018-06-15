package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

@Slf4j
public class CountDownLatchExample2 {

    //线程数
    public static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 0 ; i < threadCount; i++){
            final  int threadNum = i;
            executor.execute(()->{
                try {
                    test( threadNum);
                } catch (InterruptedException e) {
                    log.error("execption",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //给定时间
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executor.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",threadNum);
        Thread.sleep(100);
    }

}
