package com.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import javax.xml.crypto.Data;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe
 **/

@Slf4j
public class LockExample3 {

    private final Map<String, Data> map = new TreeMap<>();

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        try {
            readLock.lock();
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        try {
            readLock.lock();
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        try {
            writeLock.lock();
            return map.put(key, value);
        } finally {
            readLock.unlock();
        }
    }


}
