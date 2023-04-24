package com.example.recipeapi.strategy;

import java.util.List;

import com.example.recipeapi.model.Recipe;

public interface SearchStrategy {
    List<Recipe> search(List<Recipe> recipes, String query);
}

