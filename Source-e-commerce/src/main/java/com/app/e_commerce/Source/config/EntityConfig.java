package com.app.e_commerce.Source.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.app.e_commerce.Source.entity")
public class EntityConfig {

}
