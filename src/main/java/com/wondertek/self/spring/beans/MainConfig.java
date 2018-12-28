package com.wondertek.self.spring.beans;

import com.wondertek.self.spring.config.MyImportBeanDefinitionRegistrar;
import com.wondertek.self.spring.config.MyImportSelector;
import com.wondertek.self.spring.config.MyTypeFilter;
import org.springframework.context.annotation.*;

/**
 * useDefaultFilters:取消默认扫描
 */
@Configuration
@ComponentScans({
        @ComponentScan(
                basePackages = {"com.wondertek.self.spring.beans","com.wondertek.self.spring.config"},
                includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyTypeFilter.class)},
                useDefaultFilters = false
        )
})
@Import({MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig {

}
