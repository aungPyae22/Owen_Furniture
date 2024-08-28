package com.aungpyae22.owen_furniture.service.interfaces;

import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.entity.Order;

public interface IOrderService {

    Response addOrder(Long productId, Long userId, Order requestOrder);

    Response getAllOrders();

    Response cancelOrder(Long id);

    Response findByOrderConfirmationCode(String confirmationCode);
}
