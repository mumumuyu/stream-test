package com.lgd.juc.unsafe;

/**
 * @Description:
 * @author: LGD
 * @date:2022/6/23 17:02
 */
public abstract class equalsTest {
    public static void main(String[] args) {
        System.out.println(new Integer(12).hashCode());

        Person person1 = new Person("lgd",12);
        Person person2 = new Person("lgd",12);
        Person person3 = new Person("lgd",12);
        System.out.println(person1.equals(person2));//true 比参数
        System.out.println(person1 == person2);//false 比地址
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(person3.hashCode());

        new Thread(()->{
            System.out.println(person1.toString()+"1t");//Person@4992c594
        }).start();
        new Thread(()->{
            System.out.println(person2.toString()+"2t");//Person@469dad33
        }).start();
        new Thread(()->{
            System.out.println(person3.toString()+"3t");//Person@16187bcd
        }).start();
        System.out.println(person3.toString());//Person@469dad33
        System.out.println(person2.toString());//Person@469dad33
        System.out.println(person1.toString());//Person@4992c594
    }
}
