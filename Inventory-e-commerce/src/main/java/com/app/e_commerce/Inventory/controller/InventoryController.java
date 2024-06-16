package com.app.e_commerce.Inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.e_commerce.Inventory.dto.InventorySearchRequest;
import com.app.e_commerce.Inventory.service.InventoryService;
import com.app.e_commerce.Source.dto.Response;
import com.app.e_commerce.Source.dto.SearchResponse;
import com.app.e_commerce.Source.utils.ResponseUtil;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
	private ResponseUtil responseUtil;
 
    @SuppressWarnings("rawtypes")
	@GetMapping("/search")
	public Response<SearchResponse> search(InventorySearchRequest req) {

		SearchResponse response = inventoryService.searchProduct(req);

		return responseUtil.generateResponseSuccess(response);
	}
}
