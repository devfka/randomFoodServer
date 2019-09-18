package com.randomfood.food.controller;

import com.randomfood.food.service.RecipeInredientMatrixService;
import com.randomfood.food.service.RecipeService;
import com.randomfood.food.types.RecipeDTO;
import com.randomfood.food.types.RecipeIngredientsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecipeController {

    private final Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeInredientMatrixService recipeInredientMatrixService;


    @GetMapping(value = "/getRecipes")
    public ResponseEntity<List<RecipeDTO>> getRecipes() {
        return new ResponseEntity<List<RecipeDTO>>(this.recipeService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addRecipe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeIngredientsDTO> addRecipe(@Valid @RequestBody RecipeIngredientsDTO recipeIngredient) {
        log.debug("REST request to save Ingredient : {}", recipeIngredient);
        log.debug("REST request to save Ingredient : {}", recipeIngredient);

        recipeInredientMatrixService.saveRecipeAndIngredients(recipeIngredient);

        return new ResponseEntity<RecipeIngredientsDTO>(recipeIngredient, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteRecipe/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") long id) {
        log.debug("REST request to delete recipe : {}", id);

        recipeInredientMatrixService.deleteRecipeByRecipeId(id);

        return new ResponseEntity(id, HttpStatus.OK);
    }

}

