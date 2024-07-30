package com.example.pharmacy.controllers;

import com.example.pharmacy.model.Category;
import com.example.pharmacy.model.Medicine;
import com.example.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/")
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        Medicine savedMedicine = medicineService.saveMedicine(medicine);
        return ResponseEntity.ok(savedMedicine);
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        return medicine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<Medicine>> getMedicineByTitle(@PathVariable String title) {
        List<Medicine> medicines = medicineService.getMedicineByTitle(title);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/category={name}")
    public ResponseEntity<List<Medicine>> getMedicinesByCategoryName(@PathVariable String name) {
        return ResponseEntity.ok(medicineService.findMedicinesByCategoryName(name));
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PutMapping("/id={id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicine) {
        Medicine updatedMedicine = medicineService.updateMedicine(id, medicine);
        return ResponseEntity.ok(updatedMedicine);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Medicine>> findMedicinesByCategoryName(@RequestParam String categoryName) {
        List<Medicine> medicines = medicineService.findMedicinesByCategoryName(categoryName);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Medicine>> findAllSortedByPrice() {
        List<Medicine> medicines = medicineService.findAllSortedByPrice();
        return ResponseEntity.ok(medicines);
    }
}
