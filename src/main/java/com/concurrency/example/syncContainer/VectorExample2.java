package com.concurrency.example.syncContainer;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.Vector;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

@NotThreadSafe
@Slf4j
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            };

            thread.start();
            thread2.start();
        }

    }

}
