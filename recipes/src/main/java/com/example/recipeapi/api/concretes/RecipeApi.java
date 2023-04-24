package com.example.recipeapi.api.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipeapi.api.abstracts.IRecipeApi;
import com.example.recipeapi.controller.concretes.RecipeController;
import com.example.recipeapi.model.Recipe;

@RestController
@RequestMapping("/api/recipes")
public class RecipeApi implements IRecipeApi {

    @Autowired
    private RecipeController recipeController;

    @Override
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeController.getAllRecipes();
    }

    @Override
    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeController.addRecipe(recipe);
    }

    @GetMapping("/category")
    public List<Recipe> getRecipesByCategoryName(@RequestParam String categoryName) {
        return recipeController.getRecipesByCategoryName(categoryName);
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String query) {
        return recipeController.searchRecipes(query);
    }
}
