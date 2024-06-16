package com.app.e_commerce.Inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    
    @NotBlank(message = "productName cannot null/empty")
    private String productName;

    @NotNull(message = "productPrice cannot null")
    @Min(value = 1, message = "productPrice must be greater than or equal to 1")
    private Double productPrice;

    @Min(value = 1, message = "productQty must be greater than or equal to 1")
    @NotNull(message = "productQty cannot null")
    private Integer productQty;

}
