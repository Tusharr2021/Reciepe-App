package com.example.recipeapp.controller;

import com.example.recipeapp.dto.RecipeRecommendation;
import com.example.recipeapp.dto.RecommendationRequest;
import com.example.recipeapp.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // POST /api/recommendations  { "availableIngredients": ["egg", "flour"] }
    // Returns every recipe with at least one matching ingredient, best match first.
    @PostMapping
    public List<RecipeRecommendation> recommend(@Valid @RequestBody RecommendationRequest request) {
        return recommendationService.recommend(request.getAvailableIngredients());
    }
}
