package com.aungpyae22.owen_furniture.repository;

import com.aungpyae22.owen_furniture.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT DISTINCT p.productType FROM Product p")
    List<String> findByDistinctProductType();

    @Query("SELECT p FROM Product p WHERE p.productType LIKE %:productType% AND p.availableProduct != 'false'")
    List<Product> findAvailableProductsByType(String productType);

    @Query("SELECT p FROM Product p WHERE p.availableProduct != 'true'")
    List<Product> findAllAvailableProducts();
}
