package com.randomfood.food.service;

import com.randomfood.food.modal.Ingredient;
import com.randomfood.food.types.IngredientDTO;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<IngredientDTO> findAll();

    Optional<Ingredient> findByIngredientId (Long ingredientId);

    Ingredient saveIngredient(IngredientDTO ingredientDTO);
}
