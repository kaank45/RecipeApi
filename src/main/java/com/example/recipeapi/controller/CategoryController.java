package com.example.recipeapi.controller;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {
    private final RecipeRepository recipeRepository;

    public CategoryController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return recipeRepository.findAll().stream().map(Recipe::getCategory).distinct().collect(Collectors.toList());
    }
}
