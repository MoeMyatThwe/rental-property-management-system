//RegisterRequest
package com.nwe.rentalpropertybackend.dto.auth;

import lombok.*;

@Getter
@Setter

public class RegisterRequest {

    private String fullName;

    private String email;

    private String password;

    private String country;

    private String countryCode;

    private String phoneNumber;
}
