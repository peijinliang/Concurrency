package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

@Slf4j
public class SemaphoreExample4 {

    //线程数
    public static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executor.execute(() -> {
                try {

                    //尝试获取一个许可 超出5秒中以后的就放弃执行了
                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){
                        test(threadNum);
                        semaphore.release();     //释放一个许可
                    }
                    //没有获取到许可就丢弃执行了

                } catch (InterruptedException e) {
                    log.error("execption", e);
                }
            });
        }
        executor.shutdown();
    }


    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }


}
