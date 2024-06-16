package com.app.e_commerce.Source.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitTable {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {

        log.info("==> [Start] Create table t_product, t_order ");
        StringBuilder builder = new StringBuilder();
        builder.append(
            """
                    CREATE TABLE IF NOT EXISTS t_product (
                        id BIGINT  AUTO_INCREMENT PRIMARY KEY,
                        product_name VARCHAR(255) NOT NULL,
                        product_price DECIMAL(10,2) NOT NULL,
                        product_quantity INT NOT NULL,
                        created_by VARCHAR(255),
                        created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        changed_by VARCHAR(255),
                        changed_dt TIMESTAMP DEFAULT NULL
                    ); 
                        
                """
        );

        builder.append(
            """
                    CREATE TABLE IF NOT EXISTS t_order (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        product_id BIGINT,
                        order_quantity INT NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        created_by VARCHAR(255),
                        created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        changed_by VARCHAR(255),
                        changed_dt TIMESTAMP DEFAULT NULL,
                        FOREIGN KEY (product_id) REFERENCES t_product(id)
                    ); 
                """
        );
        

        jdbcTemplate.execute(builder.toString());
        log.info("==> [END] Create table t_product, t_order ");
    }
}
