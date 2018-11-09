package com.concurrency.example.treadLocal;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * 默认会取出当前线程的id，放到map里边去
     * @param id
     */
    public static void add(Long id) {
        requestHolder.set(id);
    }

    /**
     * 相当于传入当前线程的id，然后去底层存储的map中去取
     * @return
     */
    public static Long getId() {
        return requestHolder.get();
    }

    /**
     * 根据当前线程传入的id ，进行删除
     * 否则会内存溢出
     */
    public static void remove() {
        requestHolder.remove();
    }

}
