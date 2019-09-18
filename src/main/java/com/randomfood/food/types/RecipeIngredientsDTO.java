package com.randomfood.food.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeIngredientsDTO {

    private Long recipeId;

    private String recipeName;

    private List<IngredientDTO> ingredients = new ArrayList<>();

    private boolean editMode;

    public RecipeIngredientsDTO() {
        // Empty constructor needed for Jackson.
    }

    public String getRecipeName() {
        return recipeName;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientsDTO that = (RecipeIngredientsDTO) o;
        return Objects.equals(recipeId, that.recipeId) &&
                Objects.equals(recipeName, that.recipeName);
}

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName);
    }
}
