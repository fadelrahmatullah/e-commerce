package com.app.e_commerce.Inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson.JSON;
import com.app.e_commerce.Inventory.dto.InventorySearchRequest;
import com.app.e_commerce.Inventory.dto.InventorySearchResponse;
import com.app.e_commerce.Inventory.dto.ProductRequest;
import com.app.e_commerce.Inventory.service.InventoryService;
import com.app.e_commerce.Inventory.service.ProductService;
import com.app.e_commerce.Source.dto.SearchResponse;
import com.app.e_commerce.Source.entity.ProductEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InventoryApplicationTests {

	@Autowired 
	ProductService productService;

	@Autowired
	InventoryService inventoryService;

	@Test
	void contextLoadsAddNewProduct() {

		ProductRequest req = new ProductRequest();
		req.setProductName("Test Product");
		req.setProductPrice(1000.000);
		req.setProductQty(10);

		log.info("<< New Product :{}", JSON.toJSONString(req));


		ProductEntity product = productService.createProduct(req, "Test");
		log.info("<< Execute Service Create Product : {}", JSON.toJSONString(product));
	}

	@Test
	void contextLoadsSearchInventory() {

		InventorySearchRequest req = new InventorySearchRequest();
		req.setPageNo(1);
		req.setPageSize(100);

		SearchResponse<InventorySearchResponse> searchProduct = inventoryService.searchProduct(req);

		log.info("<< search product : {}", JSON.toJSONString(searchProduct));

	}

}
