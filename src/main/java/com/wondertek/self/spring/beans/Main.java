package com.wondertek.self.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        HelloWorld helloWorld = new HelloWorld();
//        helloWorld.setName("zhaobicheng");
        //1 创建spring IOC 的容器对象
        //ApplicationContext 代表IOC容器
        //ClassPathXmlApplicationContext 是ApplicationContext的实现类 从类路径下加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        //2 从IOC容器中获取bean
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
//        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        //3 调用hello方法
        helloWorld.hello();
    }
}
