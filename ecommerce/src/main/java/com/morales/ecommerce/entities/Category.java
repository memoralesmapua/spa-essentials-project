package com.morales.ecommerce.entities;


import com.morales.ecommerce.dtos.CategoryDto;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    private BigDecimal price;

    public CategoryDto getCategoryDto(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(name);
        categoryDto.setDescription(description);
        categoryDto.setReturnedImg(img);
        categoryDto.setPrice(price);
        return categoryDto;
    }

}
