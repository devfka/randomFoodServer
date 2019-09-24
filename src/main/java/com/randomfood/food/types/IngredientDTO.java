package com.randomfood.food.types;

import com.randomfood.food.modal.Ingredient;

public class IngredientDTO {

    private Long ingredientId;

    private String ingredientName;

    private String optional;

    public IngredientDTO() {
        // Empty constructor needed for Jackson.
    }

    public IngredientDTO(Ingredient ingredient) {
        this(ingredient.getIngredientId(), ingredient.getIngredientName());
    }

    public IngredientDTO(Long ingredientId, String ingredientName) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
    }

    public IngredientDTO(String ingredientName, String optinal) {
        this.ingredientName = ingredientName;
        this.optional = optinal;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getOptional() {
        return optional;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }


}
