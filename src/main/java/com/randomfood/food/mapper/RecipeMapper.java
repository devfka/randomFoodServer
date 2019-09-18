package com.randomfood.food.mapper;

import com.randomfood.food.modal.Recipe;
import com.randomfood.food.types.RecipeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeMapper {
    private final Logger log = LoggerFactory.getLogger(RecipeMapper.class);

    public RecipeDTO recipeToRecipeDTO(Recipe recipe) {
        return new RecipeDTO(recipe);
    }

    public List<RecipeDTO> recipesToRecipeDTOs(List<Recipe> recipes) {
        return recipes.stream().filter(Objects::nonNull)
                .map(this::recipeToRecipeDTO)
                .collect(Collectors.toList());
    }

}
