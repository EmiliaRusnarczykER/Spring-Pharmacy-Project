package com.example.pharmacy.service;
import com.example.pharmacy.model.TypeMedicine;

import java.util.List;
import java.util.Optional;
public interface TypeMedicineService {
    TypeMedicine saveTypeMedicine(TypeMedicine typeMedicine);
    Optional<TypeMedicine> getTypeMedicineById(Long id);
    List<TypeMedicine> getAllTypeMedicines();
    TypeMedicine updateTypeMedicine(Long id, TypeMedicine typeMedicine);
    void deleteTypeMedicine(Long id);
    List<TypeMedicine> findByName(String name);
    List<TypeMedicine> findAllSortedByDescription();
}

