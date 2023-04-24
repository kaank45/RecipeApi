package com.example.recipeapi.controller.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.recipeapi.controller.abstracts.IRecipeController;
import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.abstracts.IRepository;

@Controller
public class RecipeController implements IRecipeController {

    @Autowired
    private IRepository<Recipe> recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAll();
    }

    public Recipe addRecipe(Recipe recipe) {
        validateRecipe(recipe);
        ensureUniqueName(recipe.getName());
        return recipeRepository.add(recipe);
    }
    public List<Recipe> getRecipesByCategoryName(String categoryName) {
        return recipeRepository.getAll().stream()
            .filter(recipe -> recipe.getCategoryName().equalsIgnoreCase(categoryName))
            .map(recipe -> new Recipe(recipe.getId(), recipe.getName(), recipe.getCategoryName(), null, recipe.getTimeNeeded()))
            .collect(Collectors.toList());
    }

    public List<Recipe> searchRecipes(String query) {
        return recipeRepository.getAll().stream()
            .filter(recipe -> recipe.getName().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    private void validateRecipe(Recipe recipe) {
        if (recipe.getName() == null || recipe.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be empty.");
        }
        if (recipe.getDescription() == null || recipe.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Recipe description cannot be empty.");
        }
        if (recipe.getTimeNeeded() <= 0) {
            throw new IllegalArgumentException("Time needed must be greater than 0.");
        }
    }

    private void ensureUniqueName(String name) {
        List<Recipe> existingRecipes = recipeRepository.getAll();
        for (Recipe recipe : existingRecipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Recipe name must be unique.");
            }
        }
    }
}
