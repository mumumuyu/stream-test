package com.lgd.stream;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @author: LGD
 * @date:2022/6/29 11:48
 */
public class AvgAndStd {
    public static void main(String[] args) {
        List<Integer> integers = new CopyOnWriteArrayList<>();
        integers.add(55);
        integers.add(57);
        integers.add(40);
        integers.add(55);
        integers.add(33);
        integers.add(77);
        integers.add(88);
        BigDecimal std = BigDecimal.valueOf(0);
        Integer sum = integers.stream().reduce(Integer::sum).orElse(0);
        double avg = (double)sum / (double) integers.size();
        if(integers.size()>1){
            double standTDSum = 0;
            for (Integer i : integers) {
                standTDSum += Math.abs(i - avg) * Math.abs(i - avg);
            }
            std = BigDecimal.valueOf(Math.sqrt(standTDSum / (double) (integers.size() - 1))).setScale(2,5);
        }
        BigDecimal aVG = BigDecimal.valueOf(avg).setScale(2, 5);
        System.out.println(aVG);
        System.out.println(std);
    }
}
