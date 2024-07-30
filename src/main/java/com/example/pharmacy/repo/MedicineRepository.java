package com.example.pharmacy.repo;
import com.example.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    @Query("SELECT m FROM Medicine m WHERE m.title = ?1")
    List<Medicine> findByTitle(String title);
    List<Medicine> findByPlatform(String platform);
    List<Medicine> findByCategoryName(String name);

    List<Medicine> findByPriceLessThan(Double price);
}
