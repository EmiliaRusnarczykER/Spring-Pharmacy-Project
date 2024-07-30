package com.example.pharmacy.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String platform;
    private String img;

    @ManyToMany
    @JoinTable(
            name = "medicine_typemedicine",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "typemedicine_id")
    )
    @JsonIgnore
    private List<TypeMedicine> typeMedicines;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;


    // GETTER SETTER
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public List<TypeMedicine> getTypeMedicines() {
        return typeMedicines;
    }
    public void setTypeMedicines(List<TypeMedicine> typeMedicines) {
        this.typeMedicines = typeMedicines;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
