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
public class SynchronizedExample2 {

    /**
     * 修饰一个类
     */
    public static void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1---{}---{}", j, i);
            }
        }
    }

    /**
     * 修饰一个静态方法
     */
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2---{}---{}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(() -> {
            synchronizedExample1.test1(1);
        });

        service.execute(() -> {
            synchronizedExample2.test1(2);
        });
    }


}
