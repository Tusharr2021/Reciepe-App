package com.example.recipeapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * A single ingredient in the master ingredient list (e.g. "egg", "flour").
 * Recipes reference these through RecipeIngredient rather than storing
 * free-text ingredient names, so matching against "ingredients I have"
 * is a reliable lookup, not fuzzy string comparison.
 */
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingredient name cannot be empty")
    @Column(nullable = false, unique = true)
    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
