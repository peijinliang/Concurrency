package com.concurrency.example.singeton;

import com.concurrency.annoations.NotThreadSafe;
import com.concurrency.annoations.ThreadSafe;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 * 饿汉模式：
 * 单例的实例在类进行装载的时候创建
 * 缺点：
 * 1）构造函数中有过多的处理，类加载非常的慢，可能会引起性能问题
 * 2）只进行加载，没有进行调用，造成资源的浪费
 *
 * 实际使用的时候：
 * 1）私有构造函数没有过多的处理
 * 2）这个类在实际过程中肯定会被使用，这样不会有资源的浪费
 **/
@ThreadSafe
public class SingletonExample2 {

    /**
     * 这是一个私有的构造函数
     */
    private SingletonExample2() {

    }

    /**
     * 单例对象
     */
    private static SingletonExample2 instance = new SingletonExample2();

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample2 getInstance() {
        return instance;
    }


}
