//AuthController
package com.nwe.rentalpropertybackend.controller;

import com.nwe.rentalpropertybackend.dto.auth.AuthResponse;
import com.nwe.rentalpropertybackend.dto.auth.LoginRequest;
import com.nwe.rentalpropertybackend.dto.auth.RegisterRequest;
import com.nwe.rentalpropertybackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
