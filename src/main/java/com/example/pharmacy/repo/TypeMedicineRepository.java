package com.example.pharmacy.repo;
import com.example.pharmacy.model.TypeMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TypeMedicineRepository extends JpaRepository<TypeMedicine, Long> {
    List<TypeMedicine> findByName(String name);
    List<TypeMedicine> findByDescriptionContaining(String keyword);
    List<TypeMedicine> findByMedicinesTitle(String title);
}
