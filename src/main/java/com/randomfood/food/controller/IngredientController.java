package com.randomfood.food.controller;

import com.randomfood.food.service.IngredientService;
import com.randomfood.food.types.IngredientDTO;
import com.randomfood.food.types.RecipeIngredientsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IngredientController {

    private final Logger log = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private IngredientService ingredientService;

    @GetMapping(value = "/getIngredients")
    public ResponseEntity<List<IngredientDTO>> getIngredients() {
        return new ResponseEntity<List<IngredientDTO>>(this.ingredientService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/addIngredient", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientDTO> addIngredient(@Valid @RequestBody IngredientDTO ingredientDTO) {
        log.debug("REST request to save Ingredient : {}", ingredientDTO);

        ingredientService.saveIngredient(ingredientDTO);

        return new ResponseEntity<IngredientDTO>(ingredientDTO, HttpStatus.OK);
    }

}
