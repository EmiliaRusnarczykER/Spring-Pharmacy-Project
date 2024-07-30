package com.example.pharmacy.repo;
import com.example.pharmacy.model.Medicine;
import com.example.pharmacy.model.Order;
import com.example.pharmacy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>  {
    List<OrderItem> findByOrder(Order order);

    // Assuming OrderItem has a foreign key relationship with Medicine
    List<OrderItem> findByMedicine(Medicine medicine);

    List<OrderItem> findByQuantityGreaterThanEqual(Integer quantity);
}
