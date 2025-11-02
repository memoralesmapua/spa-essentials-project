package com.morales.ecommerce.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

@Data
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

    private BigDecimal price;

}


