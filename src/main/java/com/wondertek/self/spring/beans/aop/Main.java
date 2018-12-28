package com.wondertek.self.spring.beans.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-aop.xml");
        SomeService someService = (SomeService) applicationContext.getBean("someService");
        someService.doFirst();
    }
}
