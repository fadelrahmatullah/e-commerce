package com.app.e_commerce.Order.service;

import com.app.e_commerce.Order.dto.OrderRequest;
import com.app.e_commerce.Order.dto.OrderResponse;

public interface OrderService {
    
    OrderResponse orderProduct(OrderRequest req, String username);
}
