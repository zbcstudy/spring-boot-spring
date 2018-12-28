package com.wondertek.self.spring.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要导入的组件
 * 返回值就是需要导入到容器中的组件的全类名
 */
public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 当前标注@Import注解的类的所有注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[0];
    }
}
