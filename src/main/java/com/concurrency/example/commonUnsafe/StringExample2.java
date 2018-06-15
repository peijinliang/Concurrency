package com.concurrency.example.commonUnsafe;

import com.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 * <p>
 * StringBuffer  大多数方法都加了 synchronized
 * 性能有所损耗
 **/

@ThreadSafe
@Slf4j
public class StringExample2 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;


    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        //信号灯 200个信号灯  最多允许有200个线程同时访问，否则进入等待状态

        final Semaphore semaphore = new Semaphore(threadTotal);

        //总的请求数是5000

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        //总的请求数是5000
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("execption", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        log.info("builder:{}", stringBuffer.length());
        service.shutdown();
    }

    private static void update() {
        stringBuffer.append("a");
    }

}
