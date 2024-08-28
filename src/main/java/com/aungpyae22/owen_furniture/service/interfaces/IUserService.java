package com.aungpyae22.owen_furniture.service.interfaces;

import com.aungpyae22.owen_furniture.dto.LoginRequest;
import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest request);

    Response getAllUsers();

    Response getOrderHistory(String id);

    Response deleteUser(String id);

    Response getUserById(String id);

    Response getMyInfo(String email);
}
