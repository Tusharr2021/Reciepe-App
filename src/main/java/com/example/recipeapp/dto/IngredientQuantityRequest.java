package com.example.recipeapp.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * One line of a recipe's ingredient list, as sent by the client.
 * We accept a plain ingredient NAME here (not an id) - the service layer
 * looks up an existing Ingredient with that name or creates a new one.
 * This keeps the API pleasant to use: the client never has to know or
 * fetch ingredient ids up front.
 */
public class IngredientQuantityRequest {

    @NotBlank(message = "Ingredient name cannot be empty")
    private String ingredientName;

    private String quantity;

    public IngredientQuantityRequest() {
    }

    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
}
