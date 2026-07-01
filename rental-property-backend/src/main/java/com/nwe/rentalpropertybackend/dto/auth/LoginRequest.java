//LoginRequest
package com.nwe.rentalpropertybackend.dto.auth;

import lombok.*;

@Getter
@Setter
public class LoginRequest {

    private String email;

    private String password;
}
