package com.aungpyae22.owen_furniture.service.interfaces;

import com.aungpyae22.owen_furniture.dto.Response;

import java.math.BigDecimal;

public interface IProductService {

    Response addProduct(String productType, String productName, BigDecimal productPrice,String productPhotoUrl, String productDescription, String availableProduct);

    Response getAllProductType();

    Response getAllProduct();

    Response getAllAvailableProductByType(String productType);

    Response getAllAvailableProducts();

    Response deleteProduct(Long id);

    Response updateProduct(Long id,String productType, String productName, BigDecimal productPrice,String productPhotoUrl, String productDescription, String availableProduct);

    Response getProductById(Long id);


}
