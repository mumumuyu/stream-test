package com.lgd.juc.unsafe;

import lombok.Data;

/**
 * @Description:
 * @author: LGD
 * @date:2022/6/23 17:02
 */
public class equalsTest {
    public static void main(String[] args) {
        new equalsTest().lombokTest();
    }

    void equals(){
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

    void lombokTest(){
        BYD bydBlue = new BYD();
        bydBlue.setPrice(150000);
        bydBlue.setColor("蓝色");
        bydBlue.setEndurance(1200);

        BYD bydWhite = new BYD();
        bydWhite.setPrice(150000);
        bydWhite.setColor("白色");
        bydWhite.setEndurance(1200);

        /**
         * true
         * 原因是它没有调用父类的中属性，而是直接使用的子类中的属性来生成hashcode，所以当子类中的属性参数一样时，使用equals比较结果的为true。
         */
        System.out.println("两个对象比较结果:" + bydBlue.equals(bydWhite));

    }


}

@Data
class Car {
    /**
     * 颜色
     */
    private String color;
    /**
     * 续航
     */
    private Integer endurance;

}

/**
 * 比亚迪汽车
 */
@Data
class BYD extends Car {

    /**
     * 价格
     */
    private Integer price;

}
