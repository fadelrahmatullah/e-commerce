package com.app.e_commerce.Inventory.service;

import com.app.e_commerce.Inventory.dto.ProductRequest;
import com.app.e_commerce.Source.entity.ProductEntity;

public interface ProductService {
    
    ProductEntity createProduct(ProductRequest req, String username);
    ProductEntity updateProduct(Integer id, ProductRequest req, String username);
    ProductEntity getProduct(Integer id);

}
