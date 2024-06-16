package com.app.e_commerce.Inventory.dto;

import com.app.e_commerce.Source.dto.BaseSearchRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventorySearchRequest extends BaseSearchRequest{
    
    private String productName;
}
