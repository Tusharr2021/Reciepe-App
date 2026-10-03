package com.example.recipeapp.dto;

import com.example.recipeapp.model.Recipe;

import java.util.List;

/**
 * One entry in the recommendation results: a recipe plus how well it
 * matches the ingredients the user said they have.
 */
public class RecipeRecommendation {

    private Recipe recipe;
    private int matchedCount;
    private int totalRequired;
    private double matchPercentage;
    private List<String> missingIngredients;

    public RecipeRecommendation(Recipe recipe, int matchedCount, int totalRequired, List<String> missingIngredients) {
        this.recipe = recipe;
        this.matchedCount = matchedCount;
        this.totalRequired = totalRequired;
        this.matchPercentage = totalRequired == 0 ? 0 : (matchedCount * 100.0) / totalRequired;
        this.missingIngredients = missingIngredients;
    }

    public Recipe getRecipe() { return recipe; }
    public int getMatchedCount() { return matchedCount; }
    public int getTotalRequired() { return totalRequired; }
    public double getMatchPercentage() { return matchPercentage; }
    public List<String> getMissingIngredients() { return missingIngredients; }
}
