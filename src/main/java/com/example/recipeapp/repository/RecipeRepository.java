package com.example.recipeapp.repository;

import com.example.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    /**
     * Fetch all recipes with their ingredients and the linked Ingredient entity
     * in a single JOIN query, avoiding N+1 selects and ensuring the collections
     * are always populated regardless of fetch type or transaction scope.
     */
    @Query("SELECT DISTINCT r FROM Recipe r LEFT JOIN FETCH r.ingredients ri LEFT JOIN FETCH ri.ingredient")
    List<Recipe> findAllWithIngredients();
}
