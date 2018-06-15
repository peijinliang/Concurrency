package com.concurrency.example.singeton;

import com.concurrency.annoations.NotRecommend;
import com.concurrency.annoations.NotThreadSafe;
import com.concurrency.annoations.Recommend;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例的实例在第一次使用的时候创建
 **/
@NotRecommend
@NotThreadSafe
public class SingletonExample4 {

    /**
     * 这是一个私有的构造函数
     */
    private SingletonExample4() {

    }

    /** 为什么是线程不安全的了呢？
     * 在创建对象的时候，cpu指令如下。
     * 1、memory = allocate() 分配对象的内存空间
     * 2、ctorInstance() 初始化对象
     * 3、 instance = memory 设置instance指向刚分配的内存
     *
     * 多线程可能会发生指令重拍？
     * JVM 和 Cpu优化，发生了指令重拍。顺序如下：
     * 1、memory = allocate() 分配对象的内存空间
     * 3、 instance = memory 设置instance指向刚分配的内存
     * 2、ctorInstance() 初始化对象
     *
     *
     *
     */

    /**
     * 单例对象
     */
    private static SingletonExample4 instance = null;

    /**
     * 通过加锁的方式实现线程的安全
     * <p>
     * 静态的工厂方法
     *
     * @return
     */
    public static synchronized SingletonExample4 getInstance() {
        //双重检测方式
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }


}
