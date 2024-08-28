package com.aungpyae22.owen_furniture.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Long id;
    private String productType;
    private String productName;
    private BigDecimal ProductPrice;
    private String ProductPhotoUrl;
    private String ProductDescription;
    private String availableProducts;
    private List<OrderDTO> orders;
}
