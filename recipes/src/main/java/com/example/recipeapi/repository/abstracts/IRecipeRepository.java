package com.example.recipeapi.repository.abstracts;

import java.util.List;

import com.example.recipeapi.model.Recipe;

public interface IRecipeRepository extends IRepository<Recipe> {
    List<Recipe> getRecipesByCategoryName(String categoryName);
    List<Recipe> searchRecipes(String query);
}
