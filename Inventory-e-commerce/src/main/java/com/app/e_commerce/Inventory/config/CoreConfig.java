package com.app.e_commerce.Inventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(com.app.e_commerce.Source.config.CoreConfig.class)
public class CoreConfig {

}
