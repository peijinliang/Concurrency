package com.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Crete by Marlon
 * Create Date: 2018/6/13
 * Class Describe
 **/

@Slf4j
public class AtomicExample2 {

    //线程总数
    private static int threadTotal = 200;
    //客户端总数进行请求
    private static int clientTotal = 5000;

    //一个具有原子性的Long 包装类
    private static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("execption", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("count:{}", count.get());
        service.shutdown();
    }

    public static void add() {
        //先加后返回值
        count.incrementAndGet();
        //先得到返回值再加
//      count.getAndIncrement();
    }


}
