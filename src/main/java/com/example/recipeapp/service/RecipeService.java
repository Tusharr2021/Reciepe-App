package com.example.recipeapp.service;

import com.example.recipeapp.dto.IngredientQuantityRequest;
import com.example.recipeapp.dto.RecipeRequest;
import com.example.recipeapp.exception.ResourceNotFoundException;
import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.model.RecipeIngredient;
import com.example.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", id));
    }

    public Recipe createRecipe(RecipeRequest request) {
        Recipe recipe = new Recipe();
        applyRequestToRecipe(request, recipe);
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, RecipeRequest request) {
        Recipe recipe = getRecipeById(id);
        applyRequestToRecipe(request, recipe);
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = getRecipeById(id);
        recipeRepository.delete(recipe);
    }

    /**
     * Copies the plain fields across, then resolves each ingredient NAME
     * in the request to a real Ingredient (creating it if it doesn't
     * exist yet) and rebuilds the recipe's ingredient list.
     */
    private void applyRequestToRecipe(RecipeRequest request, Recipe recipe) {
        recipe.setTitle(request.getTitle());
        recipe.setCuisine(request.getCuisine());
        recipe.setDifficulty(request.getDifficulty());
        recipe.setCookingTimeMinutes(request.getCookingTimeMinutes());
        recipe.setInstructions(request.getInstructions());

        // Clear existing lines (orphanRemoval will delete the old rows on save)
        recipe.getIngredients().clear();

        List<RecipeIngredient> resolved = new ArrayList<>();
        for (IngredientQuantityRequest line : request.getIngredients()) {
            Ingredient ingredient = ingredientService.findOrCreate(line.getIngredientName());
            resolved.add(new RecipeIngredient(recipe, ingredient, line.getQuantity()));
        }
        recipe.getIngredients().addAll(resolved);
    }
}
