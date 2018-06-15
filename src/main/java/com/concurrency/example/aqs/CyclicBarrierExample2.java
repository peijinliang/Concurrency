package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

@Slf4j
public class CyclicBarrierExample2 {

    //告诉我有几个线程进行同步等待

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                  log.error("exception",e);
                }

            });
        }
        executor.shutdown();
    }

    private static  void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready ", threadNum);
        try{
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException | TimeoutException e){
            log.warn("BrokenBarrierException",e);
        }
        log.info("{} is continue ",threadNum);
    }

}
