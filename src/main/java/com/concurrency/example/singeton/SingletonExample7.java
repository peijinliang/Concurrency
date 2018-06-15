package com.concurrency.example.singeton;

import com.concurrency.annoations.Recommend;
import com.concurrency.annoations.ThreadSafe;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 * 枚举模式
 * 推荐的安全的
 **/

@ThreadSafe
@Recommend
public class SingletonExample7 {

    /**
     * 这是一个私有的构造函数
     */
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTATNCE.getInstance();
    }


    private enum Singleton {

        INSTATNCE;

        private SingletonExample7 singleton;

        /**
         * JVM保证只被调用一次，而且是绝对的
         */
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }

    }


}
