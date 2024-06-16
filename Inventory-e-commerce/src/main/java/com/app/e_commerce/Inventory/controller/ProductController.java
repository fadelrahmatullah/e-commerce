package com.app.e_commerce.Inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.e_commerce.Inventory.dto.ProductRequest;
import com.app.e_commerce.Inventory.service.ProductService;
import com.app.e_commerce.Source.dto.Response;
import com.app.e_commerce.Source.entity.ProductEntity;
import com.app.e_commerce.Source.utils.ResponseUtil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("product")
public class ProductController {
    
    @Autowired
	private ResponseUtil responseUtil;

    @Autowired
    private ProductService productService;
 
	@PostMapping("/add")
	public Response<ProductEntity> create(@RequestBody @Valid ProductRequest req) {
		Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return responseUtil.generateResponseSuccess(productService.createProduct(req, jwt.getSubject()));
	}

    @PutMapping("/update/{id}")
	public Response<ProductEntity> update(@PathVariable @NotNull Integer id, @RequestBody @Valid ProductRequest req) {
		Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return responseUtil.generateResponseSuccess(productService.updateProduct(id, req, jwt.getSubject()));
	}

    @GetMapping("/{id}")
	public Response<ProductEntity> get(@PathVariable @NotNull Integer id) {
		return responseUtil.generateResponseSuccess(productService.getProduct(id));
	}
}
