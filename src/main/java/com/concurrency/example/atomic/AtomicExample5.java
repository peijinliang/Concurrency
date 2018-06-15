package com.concurrency.example.atomic;

import com.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Crete by Marlon
 * Create Date: 2018/6/13
 * Class Describe
 **/

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    /**
     * 原子性更新某一个类的实例，指定的某一个字段
     * 并且要求这个字段必须用  volatile 修饰并且不能为 static
     */
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    public volatile int count = 100;

    public static void main(String[] args) throws InterruptedException {
        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success ,{}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success ,{}", example5.getCount());
        } else {
            log.info("update failed ,{}", example5.getCount());
        }

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
