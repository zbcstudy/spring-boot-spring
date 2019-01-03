package com.wondertek.self.spring.beans.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 1.导入spring-aop模块
 * 2.定义一个业务逻辑类
 * 3. 定义一个日志切面类
 *      类中定义一些通知方法：@before @After @AfterReturning @AfterThrowing
 * 4.给切面类的方法标注何时运行
 * 5.将切面类和业务逻辑类全部加到IOC容器中
 *
 * @EnableAspectJAutoProxy 开启注解版的注解功能
 * @Author zbc
 * @Date 20:49-2019/1/2
 */

@Configuration
@EnableAspectJAutoProxy
public class MainOfAop {

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public CalculatorAspect calculatorAspect() {
        return new CalculatorAspect();
    }
}
