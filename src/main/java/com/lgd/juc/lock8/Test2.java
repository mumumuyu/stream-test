package com.lgd.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 3.增加一个普通void先执行哪个？
 *                  这里没有锁，就不是同步方法，不受锁的影响
 *                  4.两个对象，两把锁，都被两位拿到，所以都可以执行
 *                  所以synchronized锁的是this.Class这个对象
 *
 * @author: LGD
 * @date:2022/6/23 14:05
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();
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

class Phone2{
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

    //这里没有锁，就不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
