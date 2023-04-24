package com.example.recipeapi.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recipeapi.controller.concretes.RecipeController;
import com.example.recipeapi.model.Recipe;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecipeTests {

    @Autowired
    private RecipeController recipeController;

    @BeforeEach
    public void setUp() {
        // Gerekirse her testten önce çalışacak kodu buraya ekleyin
    }

    @Test
    public void testGetAllRecipes() {
        List<Recipe> recipes = recipeController.getAllRecipes();
        assertNotNull(recipes);
        assertTrue(recipes.size() > 0);
    }

    @Test
    public void testAddRecipe() {
        Recipe recipe = new Recipe(0, "Test Recipe", "Test Category", "Test Description", 30);
        Recipe addedRecipe = recipeController.addRecipe(recipe);
        assertNotNull(addedRecipe);
        assertEquals(recipe.getName(), addedRecipe.getName());
    }
    @Test
    public void testGetRecipesByCategoryName() {
        String categoryName = "Test Category";
        List<Recipe> recipes = recipeController.getRecipesByCategoryName(categoryName);
        assertNotNull(recipes);
        for (Recipe recipe : recipes) {
            assertEquals(categoryName, recipe.getCategoryName());
        }
    }

    @Test
    public void testSearchRecipes() {
        String query = "Test";
        List<Recipe> recipes = recipeController.searchRecipes(query);
        assertNotNull(recipes);
        for (Recipe recipe : recipes) {
            assertTrue(recipe.getName().contains(query));
        }
    }
}

