package com.example.simple_jwt_login.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
