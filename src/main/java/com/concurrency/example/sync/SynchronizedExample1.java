package com.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 **/

@Slf4j
public class SynchronizedExample1 {

    /**
     * 修饰一个代码块
     */
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1---{}---{}", j, i);
            }
        }
    }

    /**
     * 修饰一个方法
     */
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2---{}---{}", j, i);
        }
    }


    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(() -> {
            synchronizedExample1.test1(1);
        });

        service.execute(() -> {
            synchronizedExample2.test2(2);
        });
    }


}
