package com.app.e_commerce.Inventory.service;

import com.app.e_commerce.Inventory.dto.InventorySearchRequest;
import com.app.e_commerce.Inventory.dto.InventorySearchResponse;
import com.app.e_commerce.Source.dto.SearchResponse;

public interface InventoryService {
    
    SearchResponse<InventorySearchResponse> searchProduct(InventorySearchRequest inventorySearchRequest);
}
