package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Category;
import com.example.pharmacy.model.Medicine;
import com.example.pharmacy.repo.CategoryRepository;
import com.example.pharmacy.repo.MedicineRepository;
import com.example.pharmacy.service.MedicineService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    //GET
    @Override
    public Optional<Medicine> getMedicineById(Long id) {
       return medicineRepository.findById(id);
   }

    @Override
    public List<Medicine> getMedicineByTitle(String title) {
        return medicineRepository.findByTitle(title);
    }

    @Override
    public List<Medicine> findMedicinesByPlatform(String name) {
        return List.of();
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> findMedicinesByCategoryName(String name) {
        return medicineRepository.findByCategoryName(name);
    }

    //POST
    @Override
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine medicine) {
        if (medicineRepository.existsById(id)) {
            medicine.setId(id);
            return medicineRepository.save(medicine);
        } else {
            throw new RuntimeException("Medicine not found");
        }
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public List<Medicine> findAllSortedByPrice() {
        return medicineRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    @Override
    public void deleteMedicineById(Long id) {
        medicineRepository.deleteById(id);
    }

    @Transactional
    public void updateMedicinePrice(Long id, Double newPrice) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
            Medicine m = medicine.get();
            m.setPrice(newPrice);
            medicineRepository.save(m);
        } else {
            throw new RuntimeException("Medicine not found");
        }
    }

    @PostConstruct
    public void init() {

        Category analgesics = categoryRepository.findByName("Analgesics");
        Category antibiotics = categoryRepository.findByName("Antibiotics");
        Category antivirals = categoryRepository.findByName("Antivirals");

        Medicine medicine1 = new Medicine();
        medicine1.setId(1L);
        medicine1.setTitle("Ibuprofen");
        medicine1.setDescription("Nonsteroidal anti-inflammatory drug used for treating pain and inflammation.");
        medicine1.setPrice(19.99);
        medicine1.setCategory(analgesics);

        Medicine medicine2 = new Medicine();
        medicine2.setId(2L);
        medicine2.setTitle("Amoxicillin");
        medicine2.setDescription("Antibiotic used to treat bacterial infections.");
        medicine2.setPrice(9.99);
        medicine2.setCategory(antibiotics);

        Medicine medicine3 = new Medicine();
        medicine3.setId(3L);
        medicine3.setTitle("Acyclovir");
        medicine3.setDescription("Antiviral drug used for treating herpes infections.");
        medicine3.setPrice(29.99);
        medicine3.setCategory(antivirals);

        Medicine medicine4 = new Medicine();
        medicine4.setId(4L);
        medicine4.setTitle("Paracetamol");
        medicine4.setDescription("Analgesic and antipyretic drug used for pain relief and fever reduction.");
        medicine4.setPrice(14.99);
        medicine4.setCategory(analgesics);

        Medicine medicine5 = new Medicine();
        medicine5.setId(5L);
        medicine5.setTitle("Azithromycin");
        medicine5.setDescription("Antibiotic used to treat various bacterial infections.");
        medicine5.setPrice(24.99);
        medicine5.setCategory(antibiotics);

        medicineRepository.save(medicine1);
        medicineRepository.save(medicine2);
        medicineRepository.save(medicine3);
        medicineRepository.save(medicine4);
        medicineRepository.save(medicine5);
    }
}
