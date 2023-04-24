package com.example.recipeapi.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recipeapi.controller.concretes.CategoryController;
import com.example.recipeapi.model.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryTests {

    @Autowired
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
       // empty for now 
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = categoryController.getAllCategories();
        assertNotNull(categories);
        assertTrue(categories.size() > 0);
    }
}
