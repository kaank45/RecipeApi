package com.example.recipeapi.controller.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.recipeapi.controller.abstracts.ICategoryController;
import com.example.recipeapi.model.Category;
import com.example.recipeapi.repository.abstracts.IRepository;

@Controller
public class CategoryController implements ICategoryController {

    @Autowired
    private IRepository<Category> categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.getAll();
    }
}
