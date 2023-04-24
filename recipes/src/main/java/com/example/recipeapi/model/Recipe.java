package com.example.recipeapi.model;


public class Recipe {
    private int id;
    private String name;
    private String categoryName;
    private String description;
    private int timeNeeded;

    public Recipe(int id, String name, String categoryName, String description, int timeNeeded) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.description = description;
        this.timeNeeded = timeNeeded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(int timeNeeded) {
        this.timeNeeded = timeNeeded;
    }
}
