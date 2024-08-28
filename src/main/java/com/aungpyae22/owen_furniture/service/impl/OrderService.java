package com.aungpyae22.owen_furniture.service.impl;

import com.aungpyae22.owen_furniture.dto.OrderDTO;
import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.entity.Order;
import com.aungpyae22.owen_furniture.entity.Product;
import com.aungpyae22.owen_furniture.entity.User;
import com.aungpyae22.owen_furniture.exception.OurException;
import com.aungpyae22.owen_furniture.repository.OrderRepository;
import com.aungpyae22.owen_furniture.repository.ProductRepository;
import com.aungpyae22.owen_furniture.repository.UserRepository;
import com.aungpyae22.owen_furniture.service.interfaces.IOrderService;
import com.aungpyae22.owen_furniture.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public Response addOrder(Long productId, Long userId, Order requestOrder) {
        Response response = new Response();

        try{

            Product product = productRepo.findById(productId).orElseThrow(() -> new OurException("Can't found the product with id "));
            User user = userRepo.findById(userId).orElseThrow(() -> new OurException("Can't found the user with id "));

            requestOrder.setProduct(product);
            requestOrder.setUser(user);
            String orderConfirmationCode = Utils.generateAlphanumericString(10);
            requestOrder.setOrderConfirmationCode(orderConfirmationCode);
            Order saveOrder = orderRepo.save(requestOrder);

            OrderDTO orderDTO = Utils.mapOrderEntityToOrderDTO(saveOrder);

            response.setStatusCode(200);
            response.setMessage("successfully added order request.");
            response.setOrder(orderDTO);
        }
        catch (OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't add the order request");
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during the adding order request. "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllOrders() {
        Response response = new Response();

        try{
            List<Order> orderList = orderRepo.findAll();
            List<OrderDTO> orderDTOList = Utils.mapOrderListEntityToOrderListDTO(orderList);

            response.setStatusCode(200);
            response.setMessage("successfully got all orders.");
            response.setOrderList(orderDTOList);
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during the getting all orders. " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response cancelOrder(Long id) {
        Response response = new Response();

        try{
            orderRepo.findById(id).orElseThrow(() -> new OurException("Can't find the order"));
            orderRepo.deleteById(id);

            response.setStatusCode(200);
            response.setMessage("successfully deleted by id.");
        }
        catch (OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't find the order with id "+ id +" : "+e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during canceling the order. "+e.getMessage());
        }
        return response;
    }

    @Override
    public Response findByOrderConfirmationCode(String confirmationCode) {
        Response response = new Response();

        try{
            Order order = orderRepo.findByOrderConfirmationCode(confirmationCode).orElseThrow(() -> new OurException("Can't found the order with " + confirmationCode));
            OrderDTO orderDTO = Utils.mapOrderEntityToOrderDTO(order);
            response.setOrder(orderDTO);

            response.setStatusCode(200);
            response.setMessage("successfully founded order by confirmationCode.");
        }
        catch (OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't find the order with code "+ confirmationCode +" : "+e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during finding the order. "+e.getMessage());
        }
        return response;
    }
}
