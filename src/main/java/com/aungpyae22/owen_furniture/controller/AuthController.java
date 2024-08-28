package com.aungpyae22.owen_furniture.controller;

import com.aungpyae22.owen_furniture.dto.LoginRequest;
import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.entity.User;
import com.aungpyae22.owen_furniture.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/owen/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<Response> register(@RequestBody User user){
        Response response = userService.register(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Response> login (@RequestBody LoginRequest request){
        Response response = userService.login(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
