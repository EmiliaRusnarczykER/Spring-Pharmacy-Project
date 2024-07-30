package com.example.pharmacy.service;
import com.example.pharmacy.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review saveReview(Review review);
    Optional<Review> getReviewById(Long id);
    List<Review> getAllReviews();
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
    List<Review> findByRating(int rating);
    List<Review> findAllSortedByDate();
}
