package com.example.recipeapi.strategy;
import java.util.List;

import com.example.recipeapi.repository.RecipeRepository;

public interface ListingStrategy {
    List<String> list(RecipeRepository recipeRepository);

}
