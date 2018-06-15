package com.concurrency;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Crete by Marlon
 * Create Date: 2018/6/13
 * Class Describe
 **/

@Slf4j
public class MapExample {

    private static Map<Integer, Integer> maps = Maps.newConcurrentMap();

    //线程总数
    private static int threadTotal = 200;
    //客户端总数进行请求
    private static int clientTotal = 5000;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(threadTotal);

        for (int index = 0; index < clientTotal; index++) {
            final int threadNum = index;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        log.info("size={}", maps.size());
    }

    public static void func(int threadNum) {
        maps.put(threadNum, threadNum);
    }

}
