package com.aungpyae22.owen_furniture.controller;

import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.entity.Order;
import com.aungpyae22.owen_furniture.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/owen/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping(path = "/add/{productId}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> addOrder(@PathVariable("productId") long productId,
                                             @PathVariable("userId") long userId,
                                             @RequestBody Order requestOrder){
        Response response = orderService.addOrder(productId, userId, requestOrder);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllOrders(){
        Response response = orderService.getAllOrders();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(path = "/get-order-by-confirmation-code/{confirmationCode}")
    public ResponseEntity<Response> getOrderByConfirmationCode(@PathVariable("confirmationCode") String confirmationCode){

        Response response = orderService.findByOrderConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping(path = "/delete/{orderId}")
    public ResponseEntity<Response> deleteOrder(@PathVariable("orderId") long orderId){
        Response response = orderService.cancelOrder(orderId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
