package com.example.recipeapi.controller;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<Recipe> getAllRecipes(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (category != null) {
            return recipeRepository.findByCategory(category);
        } else if (name != null) {
            List<Recipe> result = new ArrayList<>();
            for (Recipe recipe : recipeRepository.findAll()) {
                if (recipe.getName().toLowerCase().contains(name.toLowerCase()) || recipe.getCategory().toLowerCase().contains(name.toLowerCase())) {
                    result.add(recipe);
                }
            }
            return result;
        } else {
            return recipeRepository.findAll();
        }
    }

    @GetMapping("/categories")
    public Set<String> getAllCategories() {
        return recipeRepository.findAllCategories();
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        if (recipe.getName() == null || recipe.getName().trim().isEmpty() ||
                recipe.getCategory() == null || recipe.getCategory().trim().isEmpty() ||
                recipe.getDescription() == null || recipe.getDescription().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name, category, and description fields cannot be null or empty.");
        }

        if (recipeRepository.findByName(recipe.getName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A recipe with the same name already exists.");
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe + "\n Recipe added successfully.");
    }
}
