package com.morales.ecommerce.controllers;

import com.morales.ecommerce.dtos.CategoryDto;
import com.morales.ecommerce.dtos.ProductDto;
import com.morales.ecommerce.entities.Category;
import com.morales.ecommerce.entities.Product;
import com.morales.ecommerce.repositories.CategoryRepository;
import com.morales.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = categoryRepository.findAll()
                .stream()
                .map(Category::getCategoryDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Long categoryId) {
        List<ProductDto> products = productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(Product::getProductDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }
}


