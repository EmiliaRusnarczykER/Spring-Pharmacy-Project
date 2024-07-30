package com.example.pharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pharmacy.model.CartItem;
import com.example.pharmacy.model.ShoppingCart;

import java.util.List;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    List<CartItem> findByMedicinePlatform(String platform);
    List<CartItem> findByQuantityLessThan(Double quantity);
}
