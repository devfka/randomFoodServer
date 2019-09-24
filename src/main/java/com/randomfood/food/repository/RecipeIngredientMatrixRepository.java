package com.randomfood.food.repository;

import com.randomfood.food.modal.RecipeIngredientMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Database Access Object for ingredient table.
 * <p/>
 */
public interface RecipeIngredientMatrixRepository extends JpaRepository<RecipeIngredientMatrix, Long> {

    List<RecipeIngredientMatrix> findByRecipe_RecipeId(Long recipeId);

    List<RecipeIngredientMatrix> findByIngredient_IngredientId(Long ingredientId);

    List<RecipeIngredientMatrix> findByIngredientIngredientIdIn(List<Long> selectedIngredientIdList);

    void deleteByRecipeRecipeId(long recipeId);

}
