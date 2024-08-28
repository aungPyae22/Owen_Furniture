package com.aungpyae22.owen_furniture.dto;

import com.aungpyae22.owen_furniture.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;

    private String token;
    private List<String> productTypes;
    private String role;
    private String expirationDate;
    private String orderConfirmationCode;

    private UserDTO user;
    private ProductDTO product;
    private OrderDTO order;
    private List<UserDTO> userList;
    private List<ProductDTO> productList;
    private List<OrderDTO> orderList;

}
