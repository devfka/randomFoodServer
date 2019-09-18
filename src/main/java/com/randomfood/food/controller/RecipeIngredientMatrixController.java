package com.randomfood.food.controller;

import com.randomfood.food.mapper.RecipeIngredientMapper;
import com.randomfood.food.service.RecipeInredientMatrixService;
import com.randomfood.food.types.RecipeIngredientsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RecipeIngredientMatrixController {

    private final Logger log = LoggerFactory.getLogger(RecipeIngredientMatrixController.class);

    @Autowired
    private RecipeInredientMatrixService recipeInredientMatrixService;

    @Autowired
    private RecipeIngredientMapper recipeIngredientMapper;

    @GetMapping(value = "/getRecipeIngredientsByRecipeId/{recipeId}")
    public ResponseEntity<RecipeIngredientsDTO> getRecipeIngredientsByRecipeId(@PathVariable("recipeId") Long recipeId) {

        return new ResponseEntity<RecipeIngredientsDTO>(this.recipeIngredientMapper.recipeIngredientMatrixListToRecipeByIngredientDTO
                (this.recipeInredientMatrixService.findByRecipeId(recipeId)), HttpStatus.OK);

    }

    @GetMapping(value = "/getRecipesWithIngredientList")
    public ResponseEntity<List<RecipeIngredientsDTO>> getRecipesWithIngredientList() {

        return new ResponseEntity<List<RecipeIngredientsDTO>>(this.recipeIngredientMapper.recipeIngredientMatrixListToRecipeByIngredientDTOList
                (this.recipeInredientMatrixService.findAll()), HttpStatus.OK);

    }

    @GetMapping(value = "/getAllRecipesWithIngredientListByIngredient")
    public ResponseEntity<List<RecipeIngredientsDTO>> getAllRecipesWithIngredientListByIngredient(@RequestParam List<Long> selectedIngredients, @RequestParam List<Long> unselectedIngredients) {

        return new ResponseEntity<List<RecipeIngredientsDTO>>(this.recipeIngredientMapper.recipeIngredientMatrixListToRecipeByIngredientDTOList
                (this.recipeInredientMatrixService.findByIngredientInAndAndIngredientNotIn(selectedIngredients, unselectedIngredients)), HttpStatus.OK);

    }

}
