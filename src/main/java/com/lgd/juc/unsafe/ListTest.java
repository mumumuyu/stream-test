package com.lgd.juc.unsafe;

import lombok.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Description:    //java.util.ConcurrentModificationException
 * @author: LGD
 * @date:2022/6/23 14:56
 */
public class ListTest {
    public static void main(String[] args) {
//        List<String> strings = Arrays.asList("1", "2", "3");
//        strings.forEach(System.out::println);
        //并发下ArrayList并不安全，所以需要解决方案
        //1.老集合就是Vector
        //2.使用Collections.synchronizedList(new ArrayList<>())
        //3.JUC包下ConCurrentXXX(并发的xxx)
//        List<String> strings = Collections.synchronizedList(new ArrayList<>());
        List<String> strings = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                strings.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(strings);
            },String.valueOf(i)).start();
        }
/*        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        strings.forEach(System.out::println);*/
    }
}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private String name;
    private int age;

    public boolean equals(Object anObject){
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Person) {
            Person person = (Person) anObject;
            if(person.name.equals(this.name) && person.age == this.age)
                return true;
        }
        return false;
    }
    public String toString(){
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    public int hashCode() {
        final int prime = 31;  // 基数
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;            // 相同的数据，hashCode也相同。所以age与name相同时，result计算结果也相同。
    }
}
