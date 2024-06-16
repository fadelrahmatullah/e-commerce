package com.app.e_commerce.Order.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.app.e_commerce.Order.dto.OrderRequest;
import com.app.e_commerce.Order.dto.OrderResponse;
import com.app.e_commerce.Order.service.OrderService;
import com.app.e_commerce.Source.entity.OrderEntity;
import com.app.e_commerce.Source.entity.ProductEntity;
import com.app.e_commerce.Source.exception.ValidationException;
import com.app.e_commerce.Source.repository.OrderRepository;
import com.app.e_commerce.Source.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public OrderResponse orderProduct(OrderRequest req, String username) {

        OrderResponse response = new OrderResponse();
        
        ProductEntity getProduct = productRepository.findById(req.getProductId()).orElseThrow(
            () -> {
                throw new ValidationException("NOTVALID001", req.getProductId()+" Not Found");
            }
        );

        if (getProduct.getProductQty() < req.getQuantity()) {
            throw new ValidationException("NOTVALID001", getProduct.getProductName() + " Not available");
        }
        log.info("<<Order Product :{}", JSON.toJSONString(getProduct));

        OrderEntity order = new OrderEntity();
        order.setOrderDt(new Date());
        order.setProduct(getProduct);
        order.setCreatedBy(username);
        order.setCreatedDt(new Date());
        order.setQuantity(req.getQuantity());
        orderRepository.save(order);

        getProduct.setProductQty(getProduct.getProductQty() - req.getQuantity());
        getProduct.setChangedBy(username);
        getProduct.setChangedDt(new Date());
        productRepository.save(getProduct);

        response.setProductName(getProduct.getProductName());
        response.setQuatityOrder(req.getQuantity());
        return response;
    }
    
}
