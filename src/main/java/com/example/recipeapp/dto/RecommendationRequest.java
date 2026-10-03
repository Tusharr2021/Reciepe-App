package com.example.recipeapp.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * What the client sends to ask "what can I cook with what I have?" -
 * just a plain list of ingredient names, e.g. ["egg", "flour", "milk"].
 */
public class RecommendationRequest {

    @NotEmpty(message = "Provide at least one ingredient you have")
    private List<String> availableIngredients;

    public RecommendationRequest() {
    }

    public List<String> getAvailableIngredients() { return availableIngredients; }
    public void setAvailableIngredients(List<String> availableIngredients) { this.availableIngredients = availableIngredients; }
}
