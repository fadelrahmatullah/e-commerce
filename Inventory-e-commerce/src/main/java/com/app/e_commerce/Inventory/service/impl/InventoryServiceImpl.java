package com.app.e_commerce.Inventory.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.e_commerce.Inventory.dto.InventorySearchRequest;
import com.app.e_commerce.Inventory.dto.InventorySearchResponse;
import com.app.e_commerce.Inventory.service.InventoryService;
import com.app.e_commerce.Source.dto.BaseSearchRequest;
import com.app.e_commerce.Source.dto.SearchResponse;
import com.app.e_commerce.Source.entity.ProductEntity;
import com.app.e_commerce.Source.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    @SuppressWarnings("unchecked")
    public SearchResponse<InventorySearchResponse> searchProduct(InventorySearchRequest req) {
        
        Page<ProductEntity> pages = productRepository.search(
            this.convertValueForLike(req.getProductName()),
            this.getPageable(req));
    
        return this.createSearchResponse(pages, req);
    }

    private String convertValueForLike(String value) {
        return value != null ? value : StringUtils.EMPTY;
    }

    @SuppressWarnings("rawtypes")
    private <T> SearchResponse createSearchResponse(Page<T> page, BaseSearchRequest request) {
        List<T> data = page.toList();
        log.info("Total data Inventory "+data.size());
        return new SearchResponse<>(request.getPageNo(), request.getPageSize(), data.size(), page.getTotalElements(),
                page.getTotalPages(), data);
    }

    private Pageable getPageable(BaseSearchRequest request) {
        return PageRequest.of(request.getPageNo() - 1, request.getPageSize());
    }
    
}
