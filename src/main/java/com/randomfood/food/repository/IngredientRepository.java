package com.randomfood.food.repository;

import com.randomfood.food.modal.Ingredient;
import com.randomfood.food.modal.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Database Access Object for recipe table.
 * <p/>
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByIngredientId (Long ingredientId);
}
