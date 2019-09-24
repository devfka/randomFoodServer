package com.randomfood.food.controller;

import com.randomfood.food.service.RecipeInredientMatrixService;
import com.randomfood.food.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;

abstract class BaseController {
    @Autowired
    public RecipeService recipeService;

    @Autowired
    public RecipeInredientMatrixService recipeInredientMatrixService;
}
