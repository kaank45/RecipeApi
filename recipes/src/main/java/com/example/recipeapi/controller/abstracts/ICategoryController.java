package com.example.recipeapi.controller.abstracts;

import java.util.List;
import com.example.recipeapi.model.Category;

public interface ICategoryController {
    List<Category> getAllCategories();
}
