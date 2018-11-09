package com.concurrency.example.immutable;

import com.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.Map;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 **/

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newConcurrentMap();

    static {
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        map.put(5, 6);
        // Collections.unmodifiableMap
        //在初始化的时候讲数据copy到一个 UnmodifiableMap 中去，然后调用以前的方法直接 throw 异常
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 2000);
        //java.lang.UnsupportedOperationException
        log.info("{}", map.get(1));
    }

}
