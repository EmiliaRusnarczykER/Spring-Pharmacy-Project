package com.example.pharmacy.service;

import com.example.pharmacy.model.Medicine;

import java.util.List;
import java.util.Optional;
public interface MedicineService {
    Medicine saveMedicine(Medicine medicine);

    List<Medicine> getAllMedicines();
    Optional<Medicine> getMedicineById(Long id);
    List<Medicine> getMedicineByTitle(String title);

    List<Medicine> findMedicinesByPlatform(String name);
    List<Medicine> findMedicinesByCategoryName(String name);

    Medicine updateMedicine(Long id, Medicine medicine);
    void deleteMedicine(Long id);
    List<Medicine> findAllSortedByPrice();


    void deleteMedicineById(Long id);


}
