package com.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * Crete by Marlon
 * Create Date: 2018/6/16
 * Class Describe
 * 一个简单的死锁类
 * 在DeadLock类对象flag = 1时（td1），线程先锁定01，睡眠500毫秒
 * 当td1 进入睡眠状态时，另一个flag=0对象td2线程启动，先锁定o2，睡眠500毫秒
 * 当td1 睡眠结束之后需要o2才能继续执行，而此时o2已经被td2锁定
 * 当td2 睡眠结束后需要锁定o1才能继续执行，而此时o1已经被td1锁定
 * td1 、td2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁
 **/

@Slf4j
public class DeadLock implements Runnable {

    public int flag = 1;

    //静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("1");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("0");
                }
            }
        }

    }

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();

        td1.flag = 1;
        td2.flag = 0;

        // td1,td2都处于可执行状态，但是JVM线程调度先执行那个方法不确定
        new Thread(td1).start();
        new Thread(td2).start();
    }


}
