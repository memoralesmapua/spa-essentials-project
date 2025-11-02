package com.morales.ecommerce.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class ReviewDto {
    private Long id;
    private int rating;
    private String comment;
    private Instant createdAt;
    private Long productId;
}


