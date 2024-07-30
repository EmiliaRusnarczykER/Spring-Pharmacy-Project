package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Review;
import com.example.pharmacy.repo.ReviewRepository;
import com.example.pharmacy.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    @Override
    public Review updateReview(Long id, Review review) {
        if (reviewRepository.existsById(id)) {
            review.setId(id);
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review not found");
        }
    }
    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    @Override
    public List<Review> findByRating(int rating) {
        return reviewRepository.findByRatingGreaterThanEqual(rating);
    }
    @Override
    public List<Review> findAllSortedByDate() {
        return reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }
    @Transactional
    public void updateReviewContent(Long id, String newContent) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            Review r = review.get();
            r.setComment(newContent);
            reviewRepository.save(r);
        } else {
            throw new RuntimeException("Review not found");
        }
    }
}
