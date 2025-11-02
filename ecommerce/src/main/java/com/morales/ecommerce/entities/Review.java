package com.morales.ecommerce.entities;

import com.morales.ecommerce.dtos.ReviewDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    @Column(length = 2000)
    private String comment;

    private Instant createdAt = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ReviewDto getReviewDto() {
        ReviewDto dto = new ReviewDto();
        dto.setId(id);
        dto.setRating(rating);
        dto.setComment(comment);
        dto.setCreatedAt(createdAt);
        dto.setProductId(product != null ? product.getId() : null);
        return dto;
    }
}


