package com.randomfood.food.service;

import com.randomfood.food.modal.Ingredient;
import com.randomfood.food.types.IngredientDTO;

import java.util.List;

public interface IngredientService {

    List<IngredientDTO> findAll();

    Ingredient findByIngredientId (Long ingredientId);

    Ingredient saveIngredient(IngredientDTO ingredientDTO);
}
