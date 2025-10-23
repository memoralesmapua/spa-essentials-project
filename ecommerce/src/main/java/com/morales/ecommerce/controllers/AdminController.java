package com.morales.ecommerce.controllers;


import com.morales.ecommerce.dtos.CategoryDto;
import com.morales.ecommerce.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    public ResponseEntity<CategoryDto> postCategory (@ModelAttribute CategoryDto categoryDto){

        CategoryDto createdCategoryDto = adminService.postCategory(categoryDto);
        if (createdCategoryDto == null) return Response

    }

}
