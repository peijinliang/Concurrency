package com.concurrency.example.publish;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe 对象溢出
 **/

@Slf4j
@NotThreadSafe
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            //在这个对象没有构造完成之前就会被发布，有可能有不安全的因素
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args){
        new Escape();
    }

}
