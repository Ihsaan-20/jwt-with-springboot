package com.example.simple_jwt_login.services;

import com.example.simple_jwt_login.dto.JwtResponse;
import com.example.simple_jwt_login.dto.LoginRequest;
import com.example.simple_jwt_login.dto.RegisterRequest;
import com.example.simple_jwt_login.entity.Role;
import com.example.simple_jwt_login.entity.User;
import com.example.simple_jwt_login.repo.UserRepository;
import com.example.simple_jwt_login.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {
        Role role = Role.valueOf(request.getRole().toUpperCase());
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);
    }

    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userRepository.findByUsername(request.getUsername())
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build()
                ).orElseThrow();

        String token = jwtUtil.generateToken(userDetails.getUsername());
        return new JwtResponse(token);
    }
}
