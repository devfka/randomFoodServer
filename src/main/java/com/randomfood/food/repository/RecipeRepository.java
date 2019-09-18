package com.randomfood.food.repository;

import com.randomfood.food.modal.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Database Access Object for recipe table.
 * <p/>
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByRecipeName(String name);

    Optional<Recipe> findByRecipeId(Long recipeId);

}
