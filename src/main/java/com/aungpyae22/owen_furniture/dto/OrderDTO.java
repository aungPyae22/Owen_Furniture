package com.aungpyae22.owen_furniture.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private int orderQty;
    private String orderConfirmationCode;
    private UserDTO users;
    private ProductDTO products;
}
