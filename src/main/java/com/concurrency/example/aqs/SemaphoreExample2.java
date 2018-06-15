package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

@Slf4j
public class SemaphoreExample2 {

    //线程数
    public static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executor.execute(() -> {
                try {
                    //获取多个许可在继续进行
                    semaphore.acquire(3);
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("execption", e);
                }
                //释放多个许可
                semaphore.release(3);

            });
        }
        executor.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }


}
