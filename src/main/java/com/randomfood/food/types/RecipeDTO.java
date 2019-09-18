package com.randomfood.food.types;

import com.randomfood.food.modal.Recipe;

import javax.validation.constraints.Size;

public class RecipeDTO {

    private Long recipeId;

    @Size(min = 1, max = 100)
    private String recipeName;

    public RecipeDTO() {
        // Empty constructor needed for Jackson.
    }

    public RecipeDTO(Recipe recipe) {

        this(recipe.getRecipeId(), recipe.getRecipeName());

    }

    public RecipeDTO(Long recipeId, String recipeName) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public Long getRecipeId() {
        return recipeId;
    }
}
