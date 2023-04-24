package com.example.recipeapi.api.abstracts;

import java.util.List;
import com.example.recipeapi.model.Category;

public interface ICategoryApi {
    List<Category> getAllCategories();
}
