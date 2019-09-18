package com.randomfood.food.service;

import com.randomfood.food.modal.Recipe;
import com.randomfood.food.types.RecipeDTO;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> findByRecipeName(String name);

    List<RecipeDTO> findAll();

    Optional<Recipe> findByRecipeId(Long recipeId);

    Recipe save(Recipe recipe);
}
