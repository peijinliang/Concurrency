package com.concurrency.example.publish;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Crete by Marlon
 * Create Date: 2018/6/14
 * Class Describe 安全发布对象
 **/
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c", "d"};

    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }

    public static void main(String[] args) {

        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "z";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

    }


}
