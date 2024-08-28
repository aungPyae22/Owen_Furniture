package com.aungpyae22.owen_furniture.util;

import com.aungpyae22.owen_furniture.dto.OrderDTO;
import com.aungpyae22.owen_furniture.dto.ProductDTO;
import com.aungpyae22.owen_furniture.dto.UserDTO;
import com.aungpyae22.owen_furniture.entity.Order;
import com.aungpyae22.owen_furniture.entity.Product;
import com.aungpyae22.owen_furniture.entity.User;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static  final String  ALPHANUMERIC_STRING = "ABCDEFGHIJKLNMOPQRSTUVWHYZ0123456789";

    public static final SecureRandom RANDOM = new SecureRandom();

    public static String generateAlphanumericString(int length){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0 ; i < length ; i++){
            int randomIndex = RANDOM.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static UserDTO mapUserEntityToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public static UserDTO mapUserEntityToUserDTOAndOrders(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if(!user.getOrders().isEmpty()){
            userDTO.setOrders(user.getOrders().stream().map(order ->
                                                                    mapOrderEntityToMapOrderDTOAndOrderedProduct(order,false)).collect(Collectors.toList()));
        }
        return userDTO;
    }

    private static OrderDTO mapOrderEntityToMapOrderDTOAndOrderedProduct(Order order, boolean mapUser) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setOrderQty(order.getOrderQty());
        orderDTO.setOrderConfirmationCode(order.getOrderConfirmationCode());

        if(mapUser){
            orderDTO.setUsers(Utils.mapUserEntityToUserDTO(order.getUser()));
        }

        if(order.getProduct() != null){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(order.getProduct().getId());
            productDTO.setProductType(order.getProduct().getProductType());
            productDTO.setProductName(order.getProduct().getProductName());
            productDTO.setProductPrice(order.getProduct().getProductPrice());
            productDTO.setProductPhotoUrl(order.getProduct().getProductPhotoUrl());
            productDTO.setProductDescription(order.getProduct().getProductDescription());
            productDTO.setAvailableProducts(order.getProduct().getAvailableProduct());
            orderDTO.setProducts(productDTO);
        }

        return orderDTO;
    }

    public static ProductDTO mapProductEntityToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductType(product.getProductType());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductPhotoUrl(product.getProductPhotoUrl());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setAvailableProducts(product.getAvailableProduct());

        return productDTO;
    }

    public static ProductDTO mapProductEntityToProductDTOAndOrders(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductType(product.getProductType());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductPhotoUrl(product.getProductPhotoUrl());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setAvailableProducts(product.getAvailableProduct());

        if(!product.getOrders().isEmpty()) {
            productDTO.setOrders(product.getOrders().stream().map(
                    Utils::mapOrderEntityToOrderDTO).collect(Collectors.toList()));
        }
        return productDTO;
    }

    public static OrderDTO mapOrderEntityToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setOrderQty(order.getOrderQty());
        orderDTO.setOrderConfirmationCode(order.getOrderConfirmationCode());
        return orderDTO;
    }

    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList){
        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }

    public static List<ProductDTO> mapProductListEntityToProductListDTO(List<Product> productList){
        return productList.stream().map(Utils::mapProductEntityToProductDTO).collect(Collectors.toList());
    }

    public static List<OrderDTO> mapOrderListEntityToOrderListDTO(List<Order> orderList){
        return orderList.stream().map(Utils::mapOrderEntityToOrderDTO).collect(Collectors.toList());
    }


}
