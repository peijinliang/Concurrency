package com.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 **/

@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newConcurrentMap();

    static {
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        map.put(5, 6);
    }

    //psvm  快捷键
    public static void main(String[] args) {

        //sout
//        System.out.println();
//        System.out.println();

//        基础数据类型一但赋值不可以修改
//        a = 2;
//        b = "c";

//        引用类型不可以指向另外一个对象
//        map = Maps.newConcurrentMap();

        //但是他的值是可以修改
        map.put(5, 10);

        log.info("Meg:{}" + map.get(5));
    }


    /**
     * 如果不想修改这个变量直接将参数生命为final
     *
     * @param a
     */
    private void test(final int a) {
//        a  = 1;
    }

}
