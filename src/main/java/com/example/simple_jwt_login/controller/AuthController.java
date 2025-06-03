package com.example.simple_jwt_login.controller;



import com.example.simple_jwt_login.dto.*;
import com.example.simple_jwt_login.services.AuthService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/roles")
    public List<UserView> showProjectedUsers() {
        return authService.showProjectedUsers();
    }


    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
