package com.example.pharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pharmacy.model.Order;
import com.example.pharmacy.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrder(Order order);
    List<Payment> findByAmountGreaterThanEqual(Double amount);
    List<Payment> findByPaymentMethod(String paymentMethod);
}
