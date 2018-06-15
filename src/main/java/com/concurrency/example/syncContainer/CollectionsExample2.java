package com.concurrency.example.syncContainer;

import com.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/
@ThreadSafe
@Slf4j
public class CollectionsExample2 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    //同步容器类处理

    private static Set<Integer> set = Collections.synchronizedSet( Sets.newHashSet());

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        //信号灯 200个信号灯  最多允许有200个线程同时访问，否则进入等待状态
        final Semaphore semaphore = new Semaphore(threadTotal);

        //总的请求数是5000
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        //总的请求数是5000
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("execption", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("set:{}", set.size());
    }

    private static void add(int count) {
        set.add(count);
    }


}
