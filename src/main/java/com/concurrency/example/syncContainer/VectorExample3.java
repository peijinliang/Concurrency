package com.concurrency.example.syncContainer;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * Crete by Marlon
 * Create Date: 2018/6/15
 * Class Describe
 **/

@NotThreadSafe
@Slf4j
public class VectorExample3 {

    /**
     * java.util.ConcurrentModificationException
     *
     * @param v1
     */
    private static void test1(Vector<Integer> v1) {
        for (Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    /**
     * java.util.ConcurrentModificationException
     *
     * @param v1
     */
    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    //成功
    private static void test3(Vector<Integer> v1) {
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        test1(vector);
//        test2( vector);
//        test3(vector);
    }

}
