package com.app.e_commerce.Source.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.app.e_commerce.Source.generator.CoreBeanNameGenerator;

@Configuration
@EntityScan("com.app.e_commerce.Source.entity")
@ComponentScan(value = "com.app.e_commerce.Source", nameGenerator = CoreBeanNameGenerator.class, excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CoreJobConfig.class)})
public class CoreConfig {
}
