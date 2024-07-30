package com.example.pharmacy.repo;
import com.example.pharmacy.model.CartItem;
import com.example.pharmacy.model.Medicine;
import com.example.pharmacy.model.ShoppingCart;
import com.example.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>  {
    List<ShoppingCart> findByUser(User user);
    List<ShoppingCart> findByCartItemsMedicine(Medicine medicine);
    List<CartItem> findByPriceLessThan(Double price);
}
