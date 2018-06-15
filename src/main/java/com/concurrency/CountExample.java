package com.concurrency;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Crete by Marlon
 * Create Date: 2018/6/13
 * Class Describe
 **/
@NotThreadSafe
@Slf4j
public class CountExample {

    //线程总数
    private static int threadTotal = 200;
    //客户端总数进行请求
    private static int clientTotal = 5000;

    private static int count = 0;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(threadTotal);

        for (int index = 0; index < clientTotal; index++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        System.out.println("count{}=" + count);
    }

    public static void add() {
        count++;
    }

}
