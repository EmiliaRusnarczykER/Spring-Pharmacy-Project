package com.example.pharmacy.service;
import com.example.pharmacy.model.Category;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
public interface CategoryService {
    Category saveCategory(Category category);

    Optional<Category> getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    //List<Category> findByName(String name);
    List<Category> findAllSortedByName();
}
