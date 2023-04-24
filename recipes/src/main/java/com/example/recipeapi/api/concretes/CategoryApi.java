package com.example.recipeapi.api.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipeapi.api.abstracts.ICategoryApi;
import com.example.recipeapi.controller.concretes.CategoryController;
import com.example.recipeapi.model.Category;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi implements ICategoryApi {

    @Autowired
    private CategoryController categoryController;

    @Override
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryController.getAllCategories();
    }
}
