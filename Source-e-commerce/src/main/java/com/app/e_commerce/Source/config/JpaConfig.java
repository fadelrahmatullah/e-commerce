package com.app.e_commerce.Source.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = "com.app.e_commerce.Source.repository")
public class JpaConfig{

   
}
