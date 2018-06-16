package com.concurrency.example.lock;

import com.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 *
 * 重要方法：
 * 构造方法 ReentrantLock(false); 构造公平锁或者非公平锁
 * lock.tryLock()
 * lock.tryLock(long,TimeUnit)
 * lock.isLocker()
 * lock.lockInterruptibly()  如果当前线程没有锁定
 * lock.isHeldByCurrentThread()
 * lock.isFair() 判断是否为公平锁
 *
 * lock.hasQueuedThread(Thread) 线程时候获取等待此锁定
 * lock.hasQueuedThreads() 是否有线程等待此锁定
 * lock.getHoldCount() 当前线程锁定的个数
 * 
 **/

@ThreadSafe
@Slf4j
public class LockExample2 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private final static Lock lock = new ReentrantLock();

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
        service.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }


}
