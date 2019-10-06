package com.randomfood.food.controller;

import com.randomfood.food.exceptions.EntityNotFoundException;
import com.randomfood.food.mapper.RecipeIngredientMapper;
import com.randomfood.food.types.RecipeIngredientsDTO;
import com.randomfood.food.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Controller
public class RecipeIngredientMatrixController extends BaseController{

    private final Logger log = LoggerFactory.getLogger(RecipeIngredientMatrixController.class);

    @Autowired
    private RecipeIngredientMapper recipeIngredientMapper;

    @GetMapping(value = "/getRecipeIngredientsByRecipeId/{recipeId}")
    @PreAuthorize("hasRole('" + Constants.USER_ROLE + "') OR hasRole('" + Constants.ADMIN_ROLE + "')")
    public ResponseEntity<RecipeIngredientsDTO> getRecipeIngredientsByRecipeId(@PathVariable("recipeId") Long recipeId) throws EntityNotFoundException {

        return new ResponseEntity<RecipeIngredientsDTO>(this.recipeIngredientMapper.recipeIngredientMatrixListToRecipeByIngredientDTO
                (this.recipeInredientMatrixService.findByRecipeId(recipeId)), HttpStatus.OK);

    }

    @GetMapping(value = "/getRecipesWithIngredientList")
    @PreAuthorize("hasRole('" + Constants.USER_ROLE + "') OR hasRole('" + Constants.ADMIN_ROLE + "')")
    public ResponseEntity<List<RecipeIngredientsDTO>> getRecipesWithIngredientList() {

        return new ResponseEntity<List<RecipeIngredientsDTO>>(this.recipeIngredientMapper.recipeIngredientMatrixListToRecipeByIngredientDTOList
                (this.recipeInredientMatrixService.findAll()), HttpStatus.OK);

    }

    @GetMapping(value = "/getAllRecipesWithIngredientListByIngredient")
    @PreAuthorize("hasRole('" + Constants.USER_ROLE + "') OR hasRole('" + Constants.ADMIN_ROLE + "')")
    public ResponseEntity<List<RecipeIngredientsDTO>> getAllRecipesWithIngredientListByIngredient(@RequestParam List<Long> selectedIngredients, @RequestParam List<Long> unselectedIngredients) {

        return new ResponseEntity<List<RecipeIngredientsDTO>>(this.recipeIngredientMapper.recipeIngredientMatrixListToRecipeByIngredientDTOList
                (this.recipeInredientMatrixService.findByIngredientInAndIngredientNotIn(selectedIngredients, unselectedIngredients)), HttpStatus.OK);

    }

}
