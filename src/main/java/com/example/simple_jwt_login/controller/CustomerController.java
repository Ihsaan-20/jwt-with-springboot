package com.example.simple_jwt_login.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customerDashboard() {
        return "Hello CUSTOMER";
    }
}
