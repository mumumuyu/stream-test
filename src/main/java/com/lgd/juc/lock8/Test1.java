package com.lgd.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 8锁，就是锁的8个问题
 *                 1、标准情况下，两个线程先后
 *                 当然并不是先调用就先执行，而是由于锁的存在
 *                 由于一个对象只有一个锁，所以谁先拿到锁，谁先执行
 *
 * @author: LGD
 * @date:2022/6/23 14:05
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        },"B").start();
    }

}

class Phone{
    //被synchronized修饰的对象是锁的调用者
    //现在两个方法用的是同一把锁(一个对象只可以有一把锁)，谁先拿到，谁执行
    public  synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public  synchronized void call(){
        System.out.println("call");
    }
}
