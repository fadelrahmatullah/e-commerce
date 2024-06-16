package com.app.e_commerce.Source.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.app.e_commerce.Source.generator.CoreBeanNameGenerator;
import com.app.e_commerce.Source.utils.JwtAuthenticationFilter;

@Configuration
@EntityScan("com.app.e_commerce.Source.entity")
@ComponentScan(value = { "com.app.e_commerce.Source.config", "com.app.e_commerce.Source.repository",
		"com.app.e_commerce.Source.service", "com.app.e_commerce.Source.util"}, 
		nameGenerator = CoreBeanNameGenerator.class, 
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CoreConfig.class),
				@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class)})
public class CoreJobConfig {
}
