package com.example.recipeapi.test;

import com.example.recipeapi.controller.RecipeController;
import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    public void getAllRecipesTest() throws Exception {
        Recipe recipe1 = new Recipe(1L, "Recipe 1", "Category 1", "Description 1");
        Recipe recipe2 = new Recipe(2L, "Recipe 2", "Category 2", "Description 2");

        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe1, recipe2));

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(recipe1.getName()))
                .andExpect(jsonPath("$[1].name").value(recipe2.getName()));
    }
    
    @Test
    public void getAllRecipesByCategoryTest() throws Exception {
        Recipe recipe1 = new Recipe(1L, "Recipe 1", "Category 1", "Description 1");
        Recipe recipe2 = new Recipe(2L, "Recipe 2", "Category 2", "Description 2");

        when(recipeRepository.findByCategory("Category 1")).thenReturn(Arrays.asList(recipe1));

        mockMvc.perform(get("/recipes").param("category", "Category 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(recipe1.getName()))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void getRecipeByNameTest() throws Exception {
        Recipe recipe = new Recipe(1L, "Recipe 1", "Category 1", "Description 1");

        when(recipeRepository.findByName("Recipe 1")).thenReturn(recipe);

        mockMvc.perform(get("/recipes").param("name", "Recipe 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(recipe.getName()))
                .andExpect(jsonPath("$.length()").value(1));
    }
    
    @Test
    public void getAllCategoriesTest() throws Exception {
        Set<String> categories = new HashSet<>(Arrays.asList("Category 1", "Category 2"));

        when(recipeRepository.findAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/recipes/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$", containsInAnyOrder("Category 1", "Category 2")));
    }
    
    @Test
    public void addRecipeInvalidDataTest() throws Exception {
        Recipe invalidRecipe = new Recipe(null, "", "Category 1", "Description 1");

        mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(invalidRecipe)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void addRecipeConflictTest() throws Exception {
        Recipe existingRecipe = new Recipe(1L, "Recipe 1", "Category 1", "Description 1");
        Recipe conflictingRecipe = new Recipe(null, "Recipe 1", "Category 1", "Description 2");

        when(recipeRepository.findByName("Recipe 1")).thenReturn(existingRecipe);

        mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(conflictingRecipe)))
                .andExpect(status().isConflict());
    }
    @Test
    public void addRecipeSuccessTest() throws Exception {
        Recipe newRecipe = new Recipe(null, "New Recipe", "Category 1", "Description 1");
        Recipe savedRecipe = new Recipe(1L, "New Recipe", "Category 1", "Description 1");

        when(recipeRepository.findByName("New Recipe")).thenReturn(null);
        when(recipeRepository.save(newRecipe)).thenReturn(savedRecipe);

        mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newRecipe)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(savedRecipe.getName()))
                .andExpect(jsonPath("$.category").value(savedRecipe.getCategory()))
                .andExpect(jsonPath("$.description").value(savedRecipe.getDescription()));
    }
}
