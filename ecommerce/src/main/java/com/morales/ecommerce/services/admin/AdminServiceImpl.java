package com.morales.ecommerce.services.admin;

import com.morales.ecommerce.dtos.CategoryDto;
import com.morales.ecommerce.entities.Category;
import com.morales.ecommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());
        Category createdCategory = categoryRepository.save(category);
        CategoryDto createdCategoryDto = new CategoryDto();
        createdCategoryDto.setId(createdCategory.getId());
        return createdCategoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTitle(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }
}


