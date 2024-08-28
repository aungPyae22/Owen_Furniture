package com.aungpyae22.owen_furniture.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {
    @NotBlank(message = "email is require")
    private String email;
    @NotBlank(message = "password is require")
    private String password;
}
