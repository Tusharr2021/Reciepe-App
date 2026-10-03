package com.example.recipeapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * What the client sends to create or update a recipe. This is a DTO
 * (Data Transfer Object) rather than the Recipe entity itself - a good
 * practice to highlight, since it decouples the API's shape from the
 * database's shape (and lets us accept ingredient names instead of ids).
 */
public class RecipeRequest {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String cuisine;

    private String difficulty;

    private int cookingTimeMinutes;

    private String instructions;

    @NotEmpty(message = "A recipe needs at least one ingredient")
    private List<@Valid IngredientQuantityRequest> ingredients;

    public RecipeRequest() {
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCuisine() { return cuisine; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public int getCookingTimeMinutes() { return cookingTimeMinutes; }
    public void setCookingTimeMinutes(int cookingTimeMinutes) { this.cookingTimeMinutes = cookingTimeMinutes; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public List<IngredientQuantityRequest> getIngredients() { return ingredients; }
    public void setIngredients(List<IngredientQuantityRequest> ingredients) { this.ingredients = ingredients; }
}
