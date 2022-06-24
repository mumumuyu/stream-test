package com.lgd.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 7.一个对象，两个同步方法，一个静态一个普通
 *                 这次两个方法锁的不是一个，上面static锁的是class模板
 *                 而普通synchornized锁的是当前类对象
 * @author: LGD
 * @date:2022/6/23 14:05
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(()->{
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }

}

class Phone4{
    //静态同步锁的是Class类模板
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    //普通同步锁的是当前类对象this.class
    public synchronized void call(){
        System.out.println("call");
    }

    public void hello(){
        System.out.println("hello");
    }
}
