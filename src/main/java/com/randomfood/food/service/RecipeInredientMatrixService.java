package com.randomfood.food.service;

import com.randomfood.food.modal.RecipeIngredientMatrix;
import com.randomfood.food.types.RecipeIngredientsDTO;

import java.util.List;

public interface RecipeInredientMatrixService {

    List<RecipeIngredientMatrix> findByRecipeId(Long recipeId);

    List<RecipeIngredientMatrix> findByIngredientId(Long ingredientId);

    List<RecipeIngredientMatrix> findAll();

    List<RecipeIngredientMatrix> findByIngredientInAndAndIngredientNotIn(List<Long> selectedIngredientIdList, List<Long> unselectedIngredientIdList);

    RecipeIngredientMatrix save(RecipeIngredientMatrix RecipeIngredientMatrix);

    void saveRecipeAndIngredients(RecipeIngredientsDTO recipeIngredientsDTO);

    void deleteRecipeByRecipeId(long recipeId);
}
