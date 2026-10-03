package com.example.recipeapp.service;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return findOrCreate(ingredient.getName());
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    /**
     * Looks up an ingredient by name (case-insensitive) and returns it if
     * found; otherwise creates a new one. This is what lets the recipe
     * creation endpoint accept plain ingredient names instead of ids.
     */
    public Ingredient findOrCreate(String name) {
        String trimmed = name.trim();
        return ingredientRepository.findByNameIgnoreCase(trimmed)
                .orElseGet(() -> ingredientRepository.save(new Ingredient(trimmed)));
    }
}
