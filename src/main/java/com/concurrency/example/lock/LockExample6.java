package com.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 * Condition
 **/

@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();  //线程1 加入到AQS的等待序列中去
                log.info("wait signal"); // 1
                condition.await();  // 执行该方法之后，这个线程就在AQS的队列总移除了，相当于锁的释放  接着，他就加入到 Condition的等待队列中去 等待的该线程需要一个信号

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();  //因为线程1释放锁了，线程2获取到锁
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();  // 调用发送信号的方法  这个时候Condition队列里边有我们线程1的一个节点 于是他就被取出来了。加入到AQS的等待队列中
                                    //这个时候线程1 并没有被唤醒
            log.info("send signal ~ "); // 3    //释放锁 然后按照顺序从上到下进行唤醒线程，线程1被唤醒
            reentrantLock.unlock();
        }).start();
    }



}
