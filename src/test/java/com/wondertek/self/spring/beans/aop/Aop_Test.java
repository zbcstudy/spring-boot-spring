package com.wondertek.self.spring.beans.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zbc
 * @Date 20:57-2019/1/2
 */
public class Aop_Test {

    @Test
    public void aopTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainOfAop.class);

        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.add(1, 2);
    }
}
