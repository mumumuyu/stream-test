package com.lgd.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @Description:
 * @author: LGD
 * @date:2022/6/2 11:22
 */
public class Stream_test {

    public static Student student;
    public static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("lgd1","语文",100,1));
        students.add(new Student("lgd3","语文",300,2));
        students.add(new Student("lgd2","语文",90,1));
        students.add(new Student("lgd4","数学",100,1));
        students.add(new Student("lgd5","英文",130,1));
        students.add(new Student("lgd11","英文",130,3));
    }

    public static void main(String[] args) {
/*        for(Student student:students){
            System.out.println(student);
        }*/

        List<Student> lgd = students.stream().filter(map ->
                map.getName().toString().equals("lgd1")
        ).collect(toList());

        Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(i -> i.getName()));

/*        for(String groupName:collect.keySet()){
            System.out.println(groupName );
            collect.get(groupName).forEach(student -> System.out.println(student));
        }*/
        //group by 分组
/*        Map<String,Map<String, List<Student>>> collect2 = students.stream().collect(Collectors.groupingBy(i -> i.getName()
                ,Collectors.groupingBy(i -> i.getCount() > 1 ? "首考" : "重修" )
        ));

        for(String groupName:collect.keySet()){
            System.out.println(groupName);
            for(String count:collect2.get(groupName).keySet()) {
                System.out.println(count);
                collect2.get(groupName).get(count).forEach(student -> System.out.println(student));
            }
        }*/

/*        //where 筛选
        List<Student> newList = students.stream().filter(item -> {
            return item.getName().equals("lgd1") && BigDecimal.valueOf(item.getScore()).compareTo(new BigDecimal(100)) > 0;
        }).collect(Collectors.toList());

        for (Student student:newList) {
            System.out.println(newList);
        }*/

        /*//List转map, (k1,k2)->k2 避免键重复 k1-取第一个数据；k2-取最后一条数据
        Map<String, String> deviceMap = students.stream().collect(Collectors.toMap(i -> i.getName(), j -> j.getSubject(), (k1, k2) -> k1));

        System.out.println(deviceMap.get("lgd1"));

        //在.map里面构造数据 return什么数据就转成什么类型的list
        List<Student> collectStu = deviceMap.entrySet().stream().map(item -> {
            Student student = new Student();
            student.setName(item.getKey());
            student.setSubject(item.getValue());
            return student;
        }).collect(Collectors.toList());

        for (Student student:collectStu) {
            System.out.println(student);
        }*/

//        System.out.println(lgd.size());

        //求和与获取极值
/*        Integer sum = students.stream().mapToInt(Student::getScore).sum();
        OptionalInt optionalMax = students.stream().mapToInt(Student::getScore).max();
        System.out.println(optionalMax.getAsInt());
        System.out.println(sum);*/

/*        BigDecimal sum = students.stream().map(Student::getScore).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal max = students.stream().map(Student::getScore).reduce(BigDecimal.ZERO,BigDecimal::max);*/

/*        //找到有最大分数的对象
        Optional<Student> optional = students.stream().max(Comparator.comparing(Student::getScore));
        if (optional.isPresent()) { // 判断是否有值
            Student student = optional.get();
        }
        System.out.println(optional.orElse(new Student()));*/
/*        //去重
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("b");
        //去重
        //去重之后进行拼接:
        String s = strings.stream().distinct().collect(Collectors.joining(","));
        System.out.println(s);

        List<String> sIdList = strings.stream().distinct().collect(Collectors.toList());

        for (String s1:sIdList){
            System.out.println(s1);
        }*/

        //排序
/*        Collections.sort(students, (p1, p2) -> {
            return Integer.parseInt(String.valueOf(p1.getName().compareTo(p2.getName() + "")));
        });*/
/*        Collections.sort(students, Comparator.comparing(Student::getCount));

        for (Student student:students){
            System.out.println(student);
        }*/


        /*//统计：和、数量、最大值、最小值、平均值: List<Employee> list
        IntSummaryStatistics sumStatus = students.stream().collect(Collectors.summarizingInt(Student::getScore));
        System.out.println("和：" + sumStatus.getSum());
        System.out.println("数量：" + sumStatus.getCount());
        System.out.println("最大值：" + sumStatus.getMax());
        System.out.println("最小值：" + sumStatus.getMin());
        System.out.println("平均值：" + sumStatus.getAverage());
        OptionalDouble average = students.stream().mapToInt(Student::getScore).average();
        System.out.println(average.getAsDouble());*/

        //List<Employee> list
        Map<Integer, Long> collect3 = students.stream().collect(Collectors.groupingBy(i -> i.getScore(),Collectors.counting()));
        for(Integer integer: collect3.keySet()){
            System.out.println(integer + " " + collect3.get(integer));
        }
/*        //List<Map<String,Object>> egyList
        long count = egyList.stream()
                .filter((Map m)->StringUtils.isNotEmpty(m.get(cols)+""))
                .map((Map m)->new BigDecimal(m.get(cols)+""))
                .count();*/


/*        //全部name转大写
        long count = students.stream().map(student -> {
                    student.setName(student.getName().toUpperCase());
                    return null;
                }
        ).count();*/

/*        for(Student student:students){
            System.out.println(student);
        }*/

        /*String[] testStrings = { "java", "react", "angular", "vue","vue" };

        List<String> set = Stream.of(testStrings).distinct().collect(toList());
        //所以说，set无序，不可重复
        for (String s:set){
            System.out.println(s);
        }
        //全部转大写
        List<String> collect = Stream.of(testStrings).map(test -> test.toUpperCase()).collect(toList());

        collect.forEach(test -> System.out.println(test));

        //筛选
        long j = collect.stream().filter(p -> p.startsWith("J")).count();
        System.out.println(j);

        List<Integer> list = (List<Integer>) Stream.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(8, 9, 10, 11, 12))
                .flatMap(test -> test.stream()).collect(Collectors.toList());
        System.out.println(list.size());

        Optional<String> max = Stream.of(testStrings).max((p1, p2) -> Integer.compare(p1.length(), p2.length()));

        int sum = Stream.of(5, 6, 7, 8).reduce(0, (accumulator, element) -> accumulator + element);

        System.out.println(sum);*/
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Student {
    private String name;

    private String subject;

    private int score;
    //第几次考
    private int count;

}
