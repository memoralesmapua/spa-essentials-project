package com.morales.ecommerce.services.admin;

import com.morales.ecommerce.dtos.CategoryDto;
import com.morales.ecommerce.dtos.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByTitle(String title);

    ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;
}
