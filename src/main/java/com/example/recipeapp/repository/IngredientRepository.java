package com.example.recipeapp.repository;

import com.example.recipeapp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // Spring Data JPA generates this query from the method name:
    // "find the ingredient whose name matches, ignoring case"
    Optional<Ingredient> findByNameIgnoreCase(String name);
}
