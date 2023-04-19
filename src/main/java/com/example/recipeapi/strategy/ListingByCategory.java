package com.example.recipeapi.strategy;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListingByCategory implements ListingStrategy {

    @Override
    public List<String> list(RecipeRepository recipeRepository) {
        return recipeRepository.findAll().stream().map(Recipe::getCategory).distinct().collect(Collectors.toList());
    }
}
