package com.wondertek.self.spring.beans;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;

public class Main {
    /**
     * ApplicationContext和BeanFactory都可以用来创建spring容器，但是两者有区别：
     *  ApplicationContext:在容器进行初始化时就会创建bean的实例对象
     *      优点：响应速度快，
     *      缺点：占用内存
     *  BeanFactory：在进行容器初始化的时候并不会实例化bean对象，而是会在调用的时候初始化对象
     *      优点：占用的内存很少
     *      缺点：响应速度慢
     *
     * @param args
     */
    public static void main(String[] args) {
//        HelloWord helloWord = new HelloWord();
//        helloWord.setName("zhaobicheng");
        //1 创建spring IOC 的容器对象
        //ApplicationContext 代表IOC容器
        //ClassPathXmlApplicationContext 是ApplicationContext的实现类 从类路径下加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        //2 从IOC容器中获取bean
        HelloWord helloWord = (HelloWord) context.getBean("helloWord");
//        HelloWord helloWord = context.getBean(HelloWord.class);
        //3 调用hello方法
        helloWord.hello();
    }


    @Test
    public void beanFactoryTest() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("ApplicationContext.xml"));
        HelloWord helloWord = (HelloWord) beanFactory.getBean("helloWord");
        helloWord.hello();
    }

    /**
     * 注解配置测试
     */
    @Test
    public void annotationConfigTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String[] defaultProfiles = environment.getDefaultProfiles();
        for (String profile : defaultProfiles) {
            System.out.println(profile);
        }
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
