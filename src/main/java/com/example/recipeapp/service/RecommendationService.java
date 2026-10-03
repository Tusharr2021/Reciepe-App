package com.example.recipeapp.service;

import com.example.recipeapp.dto.RecipeRecommendation;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.model.RecipeIngredient;
import com.example.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The core feature of this app: given a list of ingredients the user
 * says they have, figure out which recipes they can make (or nearly
 * make), and rank them by how complete the match is.
 *
 * This is real logic beyond CRUD - a good thing to walk through in a
 * presentation or interview.
 */
@Service
public class RecommendationService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecommendationService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional(readOnly = true)
    public List<RecipeRecommendation> recommend(List<String> availableIngredients) {
        // Normalize to lowercase once, so matching is case-insensitive
        // and each item is compared efficiently via Set.contains().
        Set<String> available = availableIngredients.stream()
                .map(name -> name.trim().toLowerCase())
                .collect(Collectors.toSet());

        List<Recipe> allRecipes = recipeRepository.findAllWithIngredients();
        List<RecipeRecommendation> results = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            List<RecipeIngredient> required = recipe.getIngredients();
            int totalRequired = required.size();
            int matchedCount = 0;
            List<String> missing = new ArrayList<>();

            for (RecipeIngredient line : required) {
                String ingredientName = line.getIngredient().getName().toLowerCase();
                if (available.contains(ingredientName)) {
                    matchedCount++;
                } else {
                    missing.add(line.getIngredient().getName());
                }
            }

            // Only include recipes where the user has at least one
            // matching ingredient - a 0% match isn't a useful suggestion.
            if (matchedCount > 0) {
                results.add(new RecipeRecommendation(recipe, matchedCount, totalRequired, missing));
            }
        }

        // Best matches first (highest percentage, then fewest missing ingredients)
        results.sort(
                Comparator.comparingDouble(RecipeRecommendation::getMatchPercentage).reversed()
                        .thenComparingInt(r -> r.getMissingIngredients().size())
        );

        return results;
    }
}
