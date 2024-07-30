package com.example.pharmacy.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "typemedicine")
public class TypeMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "typeMedicines")
    private List<Medicine> medicines; //lista leków tego typu

    // Getters y setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Medicine> getMedicines() {
        return medicines;
    }
    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
