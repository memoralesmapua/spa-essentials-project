package com.morales.ecommerce.services.admin;

import com.morales.ecommerce.dtos.CategoryDto;

import java.io.IOException;

public interface AdminService {
    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;
}
