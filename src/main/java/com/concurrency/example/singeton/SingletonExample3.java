package com.concurrency.example.singeton;

import com.concurrency.annoations.NotRecommend;
import com.concurrency.annoations.NotThreadSafe;
import com.concurrency.annoations.ThreadSafe;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 * 懒汉模式(改造为线程安全的)：
 * 单例的实例在第一次使用的时候创建
 *
 * 但是这种方式并不推荐，因为通过加锁的方式实现，影响了性能的开销
 **/

@NotRecommend
@ThreadSafe
public class SingletonExample3 {

    /**
     * 这是一个私有的构造函数
     */
    private SingletonExample3() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 通过加锁的方式实现线程的安全
     *
     * 静态的工厂方法
     * @return
     */
    public static synchronized  SingletonExample3 getInstance() {
        //设计不太好的地方  两个线程同时访问这个方法  同时进行初始化，有可能创建两个对象
        // 有线程不全的隐患
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }


}
