package com.example.recipeapi.strategy;

import com.example.recipeapi.model.Recipe;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByCategory implements SearchStrategy {
    @Override
    public List<Recipe> search(List<Recipe> recipes, String keyword) {
        return recipes.stream()
                .filter(recipe -> recipe.getCategory().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
