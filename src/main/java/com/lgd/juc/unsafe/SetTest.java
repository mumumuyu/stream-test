package com.lgd.juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description:    //java.util.ConcurrentModificationException
 * @author: LGD
 * @date:2022/6/23 14:56
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> sets = Collections.synchronizedSet(new HashSet<>());
//        Set<String> sets = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                sets.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(sets);
            },String.valueOf(i)).start();
        }
    }
}
