package com.example.recipeapi.api.abstracts;

import java.util.List;
import com.example.recipeapi.model.Recipe;

public interface IRecipeApi {
    List<Recipe> getAllRecipes();
    Recipe addRecipe(Recipe recipe);
    List<Recipe> getRecipesByCategoryName(String categoryName);
    List<Recipe> searchRecipes(String query);
}
