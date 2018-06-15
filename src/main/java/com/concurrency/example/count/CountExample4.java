package com.concurrency.example.count;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Crete by Marlon
 * Create Date: 2018/6/13
 * Class Describe
 **/

@Slf4j
@NotThreadSafe
public class CountExample4 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    /**
     * volatile 即便用了volatile 关键字 依旧没有办法保证线程安全
     * 没有原子性
     * 适合使用场景：
     * 1）对变量的写操作不依赖于当前值
     * 2）该变量没有包含在没有其他变量的式子中 (真他妈的抽象)
     *
     *
     * 适合状态表示量
     * double Check 场景
     */
    public static volatile int count = 0;

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

    // 1、count
    // 2、 +1
    // 3、count 写回主存

    private static void add() {
        count++;
    }

}
