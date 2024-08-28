package com.aungpyae22.owen_furniture.repository;

import com.aungpyae22.owen_furniture.entity.Order;
import com.aungpyae22.owen_furniture.entity.Product;
import com.aungpyae22.owen_furniture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Product> findByProductId(Long id);
    List<User> findByUserId(Long id);
    Optional<Order> findByOrderConfirmationCode(String confirmationCode);

}
