//AuthResponse
package com.nwe.rentalpropertybackend.dto.auth;

import com.nwe.rentalpropertybackend.entity.enums.UserRole;
import lombok.*;

@Getter
@AllArgsConstructor

public class AuthResponse {

    private String token;

    private Long userId;

    private String fullName;

    private String email;

    private UserRole role;
}
