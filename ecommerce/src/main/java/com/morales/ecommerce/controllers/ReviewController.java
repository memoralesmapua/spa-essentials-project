package com.morales.ecommerce.controllers;

import com.morales.ecommerce.dtos.ReviewDto;
import com.morales.ecommerce.entities.Product;
import com.morales.ecommerce.entities.Review;
import com.morales.ecommerce.repositories.ProductRepository;
import com.morales.ecommerce.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer/products/{productId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable Long productId) {
        List<ReviewDto> reviews = reviewRepository.findAllByProductId(productId)
                .stream()
                .map(Review::getReviewDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<?> addReview(@PathVariable Long productId, @RequestBody ReviewDto reviewDto) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Review review = new Review();
        review.setProduct(productOpt.get());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        Review saved = reviewRepository.save(review);
        return ResponseEntity.ok(saved.getReviewDto());
    }
}


