package com.nwe.rentalpropertybackend.service;

import com.nwe.rentalpropertybackend.dto.auth.AuthResponse;
import com.nwe.rentalpropertybackend.dto.auth.LoginRequest;
import com.nwe.rentalpropertybackend.dto.auth.RegisterRequest;
import com.nwe.rentalpropertybackend.entity.User;
import com.nwe.rentalpropertybackend.entity.enums.UserRole;
import com.nwe.rentalpropertybackend.repository.UserRepository;
import com.nwe.rentalpropertybackend.security.JwtService;

import lombok.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request){
         if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
             throw new RuntimeException("Email already registered: "+ request.getEmail());
         }

         User user = User.builder()
                 .fullName(request.getFullName())
                 .email(request.getEmail())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .country(request.getCountry())
                 .countryCode(request.getCountryCode())
                 .phoneNumber(request.getPhoneNumber())
                 .role(UserRole.TENANT)
                 .active(true)
                 .build();

         User savedUser = userRepository.save(user);

         String token = jwtService.generateToken(savedUser);

         return new AuthResponse(
                 token,
                 savedUser.getId(),
                 savedUser.getFullName(),
                 savedUser.getEmail(),
                 savedUser.getRole()
         );
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
