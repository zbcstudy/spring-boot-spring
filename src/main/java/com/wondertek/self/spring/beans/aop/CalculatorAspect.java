package com.wondertek.self.spring.beans.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @Author zbc
 * @Date 20:36-2019/1/2
 */
@Aspect
public class CalculatorAspect {

    public static final Logger log = LoggerFactory.getLogger(CalculatorAspect.class);

    @Pointcut("execution(* com.wondertek.self.spring.beans.aop.MathCalculator.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void beforeExc(JoinPoint joinPoint) {
        log.info("{}方法运行之前..参数列表{}",joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointcut()")
    public void afterExc(JoinPoint joinPoint) {
        log.info("{}方法结束....参数列表{}",joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void afterReturnExc(Object result) {
        log.info("方法结束，返回值是：{}",result);
    }

    @AfterThrowing(pointcut = "pointcut()",throwing = "exception")
    public void afterThrowing(Exception exception) {
        log.info("方法执行异常..异常信息：{}",exception);

    }
}
