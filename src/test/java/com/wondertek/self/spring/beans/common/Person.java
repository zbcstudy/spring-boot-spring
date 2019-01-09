package com.wondertek.self.spring.beans.common;

/**
 * @Author zbc
 * @Date 22:40-2019/1/9
 */
public class Person {

    private String name;

    private int age;

    private static final int sex = 2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getSex() {
        return sex;
    }

}
