package com.example.recipeapi.repository;

import com.example.recipeapi.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RecipeRepository {

    private final Map<Long, Recipe> recipes = new HashMap<>();
    private Long nextId = 1L;

    private Long generateNewId() {
        Long id = nextId;
        nextId += 1;
        return id;
    }

    public List<Recipe> findAll() {
        return new ArrayList<>(recipes.values());
    }

    public Recipe findById(Long id) {
        return recipes.get(id);
    }

    public Recipe findByName(String name) {
        return recipes.values().stream()
            .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public List<Recipe> findByCategory(String category) {
        return recipes.values().stream()
            .filter(recipe -> recipe.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }

    public Set<String> findAllCategories() {
        return recipes.values().stream()
            .map(Recipe::getCategory)
            .collect(Collectors.toSet());
    }

    public Recipe save(Recipe recipe) {
        recipe.setId(generateNewId());
        recipes.put(recipe.getId(), recipe);
        return recipe;
    }
}
