package com.example.recipeapi.controller;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import com.example.recipeapi.strategy.ListingByCategory;
import com.example.recipeapi.strategy.ListingByName;
import com.example.recipeapi.strategy.ListingStrategy;
import com.example.recipeapi.strategy.SearchByCategory;
import com.example.recipeapi.strategy.SearchByDescription;
import com.example.recipeapi.strategy.SearchByName;
import com.example.recipeapi.strategy.SearchStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/list")
    public List<String> getList(@RequestParam(required = false) String type) {
        ListingStrategy listingStrategy;

        if (type != null && type.equalsIgnoreCase("category")) {
            listingStrategy = new ListingByCategory();
        } else {
            listingStrategy = new ListingByName();
        }

        return listingStrategy.list(recipeRepository);
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String keyword) {
        SearchStrategy searchStrategy;

        if (type != null && type.equalsIgnoreCase("name")) {
            searchStrategy = new SearchByName();
        } else if (type != null && type.equalsIgnoreCase("category")) {
            searchStrategy = new SearchByCategory();
        } else {
            searchStrategy = new SearchByDescription();
        }

        List<Recipe> allRecipes = recipeRepository.findAll();
        return searchStrategy.search(allRecipes, keyword);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
