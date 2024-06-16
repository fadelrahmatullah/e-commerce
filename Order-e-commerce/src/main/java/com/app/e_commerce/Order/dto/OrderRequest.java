package com.app.e_commerce.Order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    
    @NotNull    
    private Long productId;

    @NotNull
    private Integer quantity;

}
