package com.morales.ecommerce.dtos;

import com.morales.ecommerce.enums.UserRole;
import lombok.Data;


@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;
}
