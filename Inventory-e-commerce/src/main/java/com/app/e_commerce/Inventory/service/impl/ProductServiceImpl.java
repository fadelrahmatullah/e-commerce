package com.app.e_commerce.Inventory.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.app.e_commerce.Inventory.dto.ProductRequest;
import com.app.e_commerce.Inventory.service.ProductService;
import com.app.e_commerce.Source.entity.ProductEntity;
import com.app.e_commerce.Source.exception.ValidationException;
import com.app.e_commerce.Source.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public ProductEntity createProduct(ProductRequest req, String username) {
        
        log.info("<<Reequset New Product :{}", JSON.toJSONString(req));
        

        ProductEntity productEntity = productRepository.getProduct(req.getProductName().toLowerCase());
        if (productEntity != null) {
            throw new ValidationException("NOTVALID001", req.getProductName()+" Already Exist");
        }

        productEntity = new ProductEntity();
        BeanUtils.copyProperties(req, productEntity);
        productEntity.setCreatedBy(username);
        productEntity.setCreatedDt(new Date());
        
        productRepository.save(productEntity);

        return productEntity;
    }

    @Override
    public ProductEntity updateProduct(Integer id, ProductRequest req, String username) {

        log.info("<<Reequset Update Product :{}", JSON.toJSONString(req));
        
        ProductEntity productEntity = productRepository.findById(Long.valueOf(id)).orElseThrow(
            
            () -> {
                throw new ValidationException("NOTVALID001", id+" Not Found");
            }
        );

        BeanUtils.copyProperties(req, productEntity);
        productEntity.setChangedBy(username);
        productEntity.setChangedDt(new Date());

        productRepository.save(productEntity);

        return productEntity;
    }

    @Override
    public ProductEntity getProduct(Integer id) {
        
        ProductEntity productEntity = productRepository.findById(Long.valueOf(id)).orElseThrow(
            () -> {
                throw new ValidationException("NOTVALID001", id+" Not Found");
            }
        );
        
        return productEntity;
    }
    
    
}
