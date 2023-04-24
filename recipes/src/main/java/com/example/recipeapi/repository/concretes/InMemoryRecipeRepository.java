package com.example.recipeapi.repository.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.abstracts.IRecipeRepository;

@Repository
public class InMemoryRecipeRepository implements IRecipeRepository {

    private List<Recipe> recipes = new ArrayList<>();
    private int currentId = 1;

    public InMemoryRecipeRepository() {
        add(new Recipe(currentId++, "Pizza", "Fast Food", "Delicious pizza recipe", 20));
        add(new Recipe(currentId++, "Burger", "Fast Food", "Tasty burger recipe", 15));
        add(new Recipe(currentId++, "Ice Cream", "Dessert", "Refreshing ice cream recipe", 10));
    }

    @Override
    public List<Recipe> getAll() {
        return new ArrayList<>(recipes);
    }

    @Override
    public Recipe add(Recipe recipe) {
        recipe.setId(currentId++);
        recipes.add(recipe);
        return recipe;
    }

    public List<Recipe> getRecipesByCategoryName(String categoryName) {
        return recipes.stream()
                .filter(recipe -> recipe.getCategoryName().equalsIgnoreCase(categoryName))
                .collect(Collectors.toList());
    }

    public List<Recipe> searchRecipes(String query) {
        return recipes.stream()
                .filter(recipe -> recipe.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
