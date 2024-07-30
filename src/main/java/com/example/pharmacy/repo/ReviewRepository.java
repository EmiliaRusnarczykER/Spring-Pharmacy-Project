package com.example.pharmacy.repo;
import com.example.pharmacy.model.Medicine;
import com.example.pharmacy.model.Review;
import com.example.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>  {
    List<Review> findByUser(User user);
    List<Review> findByMedicine(Medicine medicine);
    List<Review> findByRatingGreaterThanEqual(Integer rating);
}
