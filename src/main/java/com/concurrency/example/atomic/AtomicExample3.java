package com.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Crete by Marlon
 * Create Date: 2018/6/13
 * Class Describe :
 *
 * 为什么有了 AtomicLong 还要增加一个 LongAdder ？
 * AtomicLong ：因为是cas操作，坚持用do while 操作，这种操作失败的情况非常高，造成对内存和性能下降。
 *
 * LongAdder ： 性能更高。
 * 1)前单个节点的并发分散到各个节点的，这样从而提高在高并发时候的效率
 * 2) 缺点是LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
 *
 **/
@Slf4j
public class AtomicExample3 {

    //线程总数
    private static int threadTotal = 200;
    //客户端总数进行请求
    private static int clientTotal = 5000;

    private static LongAdder count = new LongAdder();

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
        log.info("count:{}", count);
        service.shutdown();
    }

    public static void add() {
        count.increment();
    }



}
