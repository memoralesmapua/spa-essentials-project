package com.morales.ecommerce.services.admin;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.morales.ecommerce.dtos.CategoryDto;
import com.morales.ecommerce.dtos.ProductDto;
import com.morales.ecommerce.entities.Category;
import com.morales.ecommerce.entities.Product;
import com.morales.ecommerce.repositories.CategoryRepository;
import com.morales.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());
        category.setPrice(categoryDto.getPrice());
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

    @Override
    public boolean deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setPrice(categoryDto.getPrice());
            if (categoryDto.getImg() != null) {
                category.setImg(categoryDto.getImg().getBytes());
            }
            Category updatedCategory = categoryRepository.save(category);
            return updatedCategory.getCategoryDto();
        }
        return null;
    }

    //Product operations

    @Override
    public ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()){
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            product.setImg(productDto.getImg().getBytes());
            product.setCategory(optionalCategory.get());
            productRepository.save(product);
            Product createdProduct = productRepository.save(product);
            ProductDto createdProductDto = new ProductDto();
            createdProductDto.setId(createdProduct.getId());
            return createdProductDto;

        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }
}


