package com.app.e_commerce.Source.dto;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Data
public class BaseSearchRequest {
    
    @Min(1)
    private Integer pageNo = 1;
    @Min(1)
    private Integer pageSize = 10;

}
