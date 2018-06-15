package com.concurrency.example.immutable;

import com.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.Map;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 **/

@ThreadSafe
@Slf4j
public class ImmutableExample3 {

    private final static ImmutableList list = ImmutableList.of(1, 2, 3, 4);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4, 7, 5);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(2, 3).put(3, 4).build();

    public static void main(String[] args) {

        map.get(1);

        //报错 java.lang.UnsupportedOperationException
        list.add(59);
        set.add(293);

        //抛出 java.lang.UnsupportedOperationException
        map2.put(10, 30);
    }


}
