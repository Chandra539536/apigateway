package com.concrete.apigateway.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/users")
    public Map<String, String> userFallback() {
        return Map.of("message", "User Service is currently unavailable. Please try again later.");
    }
    
    @DeleteMapping("/users")
    public Map<String, String> userFallback1() {
        return Map.of("message", "User Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/orders")
    public Map<String, String> orderFallback() {
        return Map.of("message", "Order Service is currently unavailable. Please try again later.");
    }
}
