package com.app.e_commerce.Order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.e_commerce.Order.dto.OrderRequest;
import com.app.e_commerce.Order.dto.OrderResponse;
import com.app.e_commerce.Order.service.OrderService;
import com.app.e_commerce.Source.dto.Response;
import com.app.e_commerce.Source.utils.ResponseUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("order")
public class OrderController {
    
    @Autowired
	private ResponseUtil responseUtil;

    @Autowired
    private OrderService orderService;

    @PostMapping()
	public Response<OrderResponse> orderProduct(@RequestBody @Valid OrderRequest req) {
		Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return responseUtil.generateResponseSuccess(orderService.orderProduct(req, jwt.getSubject()));
	}

}
