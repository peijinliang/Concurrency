package com.concurrency.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Crete by Marlon
 * Create Date: 2018/6/19
 * Class Describe HashMap 源码解读
 **/

public class SourceCodeHashMap {

    //用法
    public static void main(String[] args) {
        //放入键值对
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "value");

        String value = hashMap.get("key");
        hashMap.clear();

        HashMap<String, String> hash1 = new HashMap<>();
        hash1.putAll(hashMap);

//      ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
//      concurrentHashMap.put();

    }

}
