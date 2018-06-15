package com.concurrency.example.singeton;

import com.concurrency.annoations.NotThreadSafe;
import com.concurrency.annoations.Recommend;
import com.concurrency.annoations.ThreadSafe;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例的实例在第一次使用的时候创建
 *
 * instance 加关键字 限制指令重拍 volatile
 **/
@Recommend
@ThreadSafe
public class SingletonExample5 {

    /**
     * 这是一个私有的构造函数
     */
    private SingletonExample5() {

    }

    /**
     * 单例对象 volatile + 双重检测机制 -> 禁止指令重拍
     */
    private volatile static SingletonExample5 instance = null;

    /**
     * 通过加锁的方式实现线程的安全
     * <p>
     * 静态的工厂方法
     * 双重检测 double check 场景
     * @return
     */
    public static synchronized SingletonExample5 getInstance() {
        //双重检测方式
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }


}
