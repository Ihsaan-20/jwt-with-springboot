package com.example.simple_jwt_login.dto;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // "ADMIN" or "CUSTOMER"
}

