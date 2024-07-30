package com.example.pharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pharmacy.model.Order;
import com.example.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByTotalAmountGreaterThan(Double amount);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
