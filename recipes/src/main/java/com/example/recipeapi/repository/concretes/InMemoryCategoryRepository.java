package com.example.recipeapi.repository.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.recipeapi.model.Category;
import com.example.recipeapi.repository.abstracts.ICategoryRepository;

@Repository
public class InMemoryCategoryRepository implements ICategoryRepository {

    private List<Category> categories = new ArrayList<>();
    private int currentId = 1;

    public InMemoryCategoryRepository() {
        add(new Category(currentId++, "Fast Food"));
        add(new Category(currentId++, "Dessert"));
        add(new Category(currentId++, "Appetizer"));
    }

    @Override
    public List<Category> getAll() {
        return new ArrayList<>(categories);
    }

    @Override
    public Category add(Category category) {
        category.setId(currentId++);
        categories.add(category);
        return category;
    }
}
