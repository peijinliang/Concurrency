package com.concurrency.example.singeton;

import com.concurrency.annoations.NotThreadSafe;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 * 懒汉模式：
 * 单例的实例在第一次使用的时候创建
 **/
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 这是一个私有的构造函数
     */
    private SingletonExample1() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     *
     * @return
     */
    public static SingletonExample1 getInstance() {
        //设计不太好的地方  两个线程同时访问这个方法  同时进行初始化，有可能创建两个对象

        // 有线程不全的隐患
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }


}
