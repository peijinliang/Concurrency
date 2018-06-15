package com.concurrency.example.singeton;

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
 * <p>
 * 实际使用的时候：
 * 1）私有构造函数没有过多的处理
 * 2）这个类在实际过程中肯定会被使用，这样不会有资源的浪费
 **/

@ThreadSafe
public class SingletonExample6 {

    /**
     * 这是一个私有的构造函数
     */
    private SingletonExample6() {

    }


    /**
     * 通过静态块进行初始化
     * 写静态方法块的时候应该看一下顺序，如果执行顺序不一样可能出现问题
     */
    static {
        instance = new SingletonExample6();
    }



    /**
     * 单例对象
     */
    private static SingletonExample6 instance = null;



    /**
     * 静态的工厂方法
     *
     * @return
     */
    public static SingletonExample6 getInstance() {
        return instance;
    }


    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }


}
