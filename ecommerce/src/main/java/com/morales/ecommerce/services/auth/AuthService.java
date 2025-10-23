package com.morales.ecommerce.services.auth;

import com.morales.ecommerce.dtos.SignupRequest;
import com.morales.ecommerce.dtos.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
