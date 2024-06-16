package com.app.e_commerce.Source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.e_commerce.Source.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    
}
