package com.concurrency.example.count;

import com.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Crete by Marlon
 * Create Date: 2018/6/13
 * Class Describe
 **/
@ThreadSafe
@Slf4j
public class CountExample2 {

    //线程总数
    private static int threadTotal = 200;
    //客户端总数进行请求
    private static int clientTotal = 5000;

    //count 是指的工作内存
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        //5000个客户端同时开始进行操作
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() -> {
                try {
                    //信号灯设置了最大值 允许200个线程进行同步操作，剩下的线程进入等待状态
                    semaphore.acquire();
                    //200个线程并发执行，以及线程私有Copy的原因 导致 丧失原子性
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("execption", e);
                }
                //每当执行完毕 count减少1
                countDownLatch.countDown();
                //但是CountDownLatch 保证了原子性的运行
                log.info("count:"+ countDownLatch.getCount());
            });
        }
        //主线程进入 阻塞状态 直到减少到0 在继续执行
        countDownLatch.await();
        log.info("count:{}", count.get());
        service.shutdown();
    }

    /**
     * 重点 Unsafe 类
     * CAS : compareAndSwapInt
     * Swap:中文的意思就是交换
     */
    public static void add() {
        //先加后返回值
        count.incrementAndGet();
        //先得到返回值再加
//      count.getAndIncrement();
    }


// 当前对象的值 var2 是否和底层的值 var5 相同，如果相同则执行操作
//    public final int getAndAddInt(Object var1, long var2, int var4) {
//        int var5;
//        do {
    // 得到底层的值 var5
//            var5 = this.getIntVolatile(var1, var2);
    //底层内存就是指的主内存
    //如果var2 于 var5相等，则底层的值变为var5 + var4
//        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
//        返回底层的值
//        return var5;
//    }


}
