package com.example.pharmacy.controllers;

import com.example.pharmacy.model.Medicine;
import com.example.pharmacy.model.TypeMedicine;
import com.example.pharmacy.service.TypeMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typemedicines")
public class TypeMedicineController {

    @Autowired
    private TypeMedicineService typeMedicineService;

    @PostMapping
    public ResponseEntity<TypeMedicine> createTypeMedicine(@RequestBody TypeMedicine typeMedicine) {
        TypeMedicine savedTypeMedicine = typeMedicineService.saveTypeMedicine(typeMedicine);
        return ResponseEntity.ok(savedTypeMedicine);
    }

    @GetMapping("/")
    public List<TypeMedicine> getAll() {
        return typeMedicineService.getAllTypeMedicines();
    }

    @GetMapping("/id={id}")
    public ResponseEntity<TypeMedicine> getTypeMedicineById(@PathVariable Long id) {
        Optional<TypeMedicine> typeMedicine = typeMedicineService.getTypeMedicineById(id);
        return typeMedicine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name={name}")
    public ResponseEntity<List<TypeMedicine>> getTypeMedicineByName(@PathVariable String name) {
        List<TypeMedicine> typeMedicines = typeMedicineService.findByName(name);
        return ResponseEntity.ok(typeMedicines);
    }

    @GetMapping
    public ResponseEntity<List<TypeMedicine>> getAllTypeMedicines() {
        List<TypeMedicine> typeMedicines = typeMedicineService.getAllTypeMedicines();
        return ResponseEntity.ok(typeMedicines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeMedicine> updateTypeMedicine(@PathVariable Long id, @RequestBody TypeMedicine typeMedicine) {
        TypeMedicine updatedTypeMedicine = typeMedicineService.updateTypeMedicine(id, typeMedicine);
        return ResponseEntity.ok(updatedTypeMedicine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeMedicine(@PathVariable Long id) {
        typeMedicineService.deleteTypeMedicine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<TypeMedicine>> findAllSortedByDescription() {
        List<TypeMedicine> typeMedicines = typeMedicineService.findAllSortedByDescription();
        return ResponseEntity.ok(typeMedicines);
    }
}
