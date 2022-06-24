package com.lgd.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 5.两个静态同步方法，只有一个对象
 *              6.两个对象，调用一个类的两个static synchronized方法
 *                  那么此时，类在ClassLoader之后得到一个全局唯一的一个Class模板
 *                  之后synchronized所的就是这个Class类模板
 *
 * @author: LGD
 * @date:2022/6/23 14:05
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();
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

class Phone3{
    //被synchronized修饰的对象是锁的调用者
    //现在两个方法用的是同一把锁(一个对象只可以有一把锁)，谁先拿到，谁执行
    //static 静态方法，在类加载的时候就有的了，Class模板
    //而phone3唯一的一个class对象，此时锁的就是class模板
    public static  synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public static synchronized void call(){
        System.out.println("call");
    }
}
