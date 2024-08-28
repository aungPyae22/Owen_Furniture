package com.aungpyae22.owen_furniture.service.impl;

import com.aungpyae22.owen_furniture.dto.LoginRequest;
import com.aungpyae22.owen_furniture.dto.OrderDTO;
import com.aungpyae22.owen_furniture.dto.Response;
import com.aungpyae22.owen_furniture.dto.UserDTO;
import com.aungpyae22.owen_furniture.entity.Order;
import com.aungpyae22.owen_furniture.entity.User;
import com.aungpyae22.owen_furniture.exception.OurException;
import com.aungpyae22.owen_furniture.repository.UserRepository;
import com.aungpyae22.owen_furniture.service.interfaces.IUserService;
import com.aungpyae22.owen_furniture.util.JwtUtils;
import com.aungpyae22.owen_furniture.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Override
    public Response register(User user) {
        Response response = new Response();

        try {

            if(user.getRole() == null && user.getRole().isBlank()){
                user.setRole("USER");
            }
            if(repo.existsByEmail(user.getEmail())){
                throw new OurException(user.getEmail() + "is already exists.");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User saveUser = repo.save(user);
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(saveUser);

            response.setStatusCode(200);
            response.setMessage("successfully registered.");
            response.setUser(userDTO);
        }
        catch(OurException e){
            response.setStatusCode(400);
            response.setMessage("Error occurs during the register. " + e.getMessage());
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurs during the register. "+e.getMessage());
        }


        return response;
    }

    @Override
    public Response login(LoginRequest request) {
        Response response = new Response();

        try{
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            User loginUser = repo.findByEmail(request.getEmail()).orElseThrow(() -> new OurException("User not found"));

            String token = jwtUtils.generateToken(loginUser);
            response.setToken(token);
            response.setRole(loginUser.getRole());
            response.setExpirationDate("7 days");
            response.setStatusCode(200);
            response.setMessage("successfully logged.");
        }
        catch (OurException e){
            response.setStatusCode(400);
            response.setMessage("Cannot login to the server. "+e.getMessage());
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during the login. "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllUsers() {
        Response response = new Response();

        try{
            List<User> userList = repo.findAll();
            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("successfully got all users.");
            response.setUserList(userDTOList);
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during getting all users. " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getOrderHistory(String id) {
        Response response = new Response();

        try{
            User user = repo.findById(Long.valueOf(id)).orElseThrow(() -> new OurException("User is not found"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTOAndOrders(user);

            response.setStatusCode(200);
            response.setMessage("successfully got history.");
            response.setUser(userDTO);
        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't find the user. " + e.getMessage());
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during getting  user's order history. " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteUser(String id) {
        Response response = new Response();

        try{
            repo.findById(Long.valueOf(id)).orElseThrow(() -> new OurException("Can't find the user."));
            repo.deleteById(Long.valueOf(id));

            response.setStatusCode(200);
            response.setMessage("successfully deleted.");
        }
        catch(OurException e){
            response.setStatusCode(400);
            response.setMessage("Can't delete the user. " + e.getMessage());
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during deleting the user. " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUserById(String id) {
        Response response = new Response();

        try{
            User user = repo.findById(Long.valueOf(id)).orElseThrow(() -> new OurException("Can't find the user."));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

            response.setStatusCode(200);
            response.setMessage("success");
            response.setUser(userDTO);
        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't find the user. " + e.getMessage());
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during finding the user. " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {
        Response response = new Response();

        try{
            User user = repo.findByEmail(email).orElseThrow(() -> new OurException("Can't found my info"));
            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

            response.setStatusCode(200);
            response.setMessage("successfully got my info");
            response.setUser(userDTO);
        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage("Can't find my info. " + e.getMessage());
        }
        catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurs during getting my info. " + e.getMessage());
        }
        return response;
    }
}
