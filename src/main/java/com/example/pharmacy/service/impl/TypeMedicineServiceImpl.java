package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.TypeMedicine;
import com.example.pharmacy.repo.TypeMedicineRepository;
import com.example.pharmacy.service.TypeMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeMedicineServiceImpl implements TypeMedicineService {

    @Autowired
    private TypeMedicineRepository typeMedicineRepository;

    @Override
    public TypeMedicine saveTypeMedicine(TypeMedicine typeMedicine) {
        return typeMedicineRepository.save(typeMedicine);
    }

    @Override
    public Optional<TypeMedicine> getTypeMedicineById(Long id) {
        return typeMedicineRepository.findById(id);
    }

    @Override
    public List<TypeMedicine> getAllTypeMedicines() {
        return typeMedicineRepository.findAll();
    }

    @Override
    public TypeMedicine updateTypeMedicine(Long id, TypeMedicine typeMedicine) {
        if (typeMedicineRepository.existsById(id)) {
            typeMedicine.setId(id);
            return typeMedicineRepository.save(typeMedicine);
        } else {
            throw new RuntimeException("TypeMedicine not found");
        }
    }

    @Override
    public void deleteTypeMedicine(Long id) {
        typeMedicineRepository.deleteById(id);
    }

    @Override
    public List<TypeMedicine> findByName(String name) {
        return typeMedicineRepository.findByName(name);
    }

    @Override
    public List<TypeMedicine> findAllSortedByDescription() {
        return typeMedicineRepository.findAll(Sort.by(Sort.Direction.ASC, "description"));
    }

    @Transactional
    public void updateTypeMedicineDescription(Long id, String newDescription) {
        Optional<TypeMedicine> typeMedicine = typeMedicineRepository.findById(id);
        if (typeMedicine.isPresent()) {
            TypeMedicine tm = typeMedicine.get();
            tm.setDescription(newDescription);
            typeMedicineRepository.save(tm);
        } else {
            throw new RuntimeException("TypeMedicine not found");
        }
    }
}
