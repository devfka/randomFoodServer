package com.randomfood.food.mapper;

import com.randomfood.food.modal.RecipeIngredientMatrix;
import com.randomfood.food.types.IngredientDTO;
import com.randomfood.food.types.RecipeIngredientsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeIngredientMapper {
    private final Logger log = LoggerFactory.getLogger(RecipeIngredientMapper.class);

    public RecipeIngredientsDTO recipeIngredientMatrixListToRecipeByIngredientDTO(List<RecipeIngredientMatrix> recipeIngredientMatrices) {

        RecipeIngredientsDTO recipeIngredientsDTO = new RecipeIngredientsDTO();

        recipeIngredientsDTO.setRecipeId(recipeIngredientMatrices.get(0).getRecipe().getRecipeId());
        recipeIngredientsDTO.setRecipeName(recipeIngredientMatrices.get(0).getRecipe().getRecipeName());

        recipeIngredientMatrices.forEach
                (recipeIngredientMatrix -> {
                    IngredientDTO ingredientDTO = new IngredientDTO();
                    ingredientDTO.setIngredientId(recipeIngredientMatrix.getIngredient().getIngredientId());
                    ingredientDTO.setIngredientName(recipeIngredientMatrix.getIngredient().getIngredientName());
                    ingredientDTO.setOptional(recipeIngredientMatrix.getOptional());
                    recipeIngredientsDTO.getIngredients().add(ingredientDTO);
                });

        return recipeIngredientsDTO;

    }

    public List<RecipeIngredientsDTO> recipeIngredientMatrixListToRecipeByIngredientDTOList(List<RecipeIngredientMatrix> recipeIngredientMatrices) {

        List<RecipeIngredientsDTO> recipeIngredientsDTOS = new ArrayList<>();

        for (RecipeIngredientMatrix outer : recipeIngredientMatrices) {

            RecipeIngredientsDTO recipeIngredientsDTO = findRecipeIngredientsDTO(recipeIngredientsDTOS, outer);

            for (RecipeIngredientMatrix inner : recipeIngredientMatrices) {
                if (outer.getRecipe().getRecipeId().equals(inner.getRecipe().getRecipeId())) {

                    if (!isIngredientAddedBefore(recipeIngredientsDTO, inner)) {
                        IngredientDTO ingredientDTO = new IngredientDTO();
                        ingredientDTO.setIngredientName(inner.getIngredient().getIngredientName());
                        ingredientDTO.setOptional(inner.getOptional());
                        recipeIngredientsDTO.getIngredients().add(ingredientDTO);
                    }
                }
            }

            if (recipeIngredientsDTOS.size() == 0) {
                recipeIngredientsDTOS.add(recipeIngredientsDTO);
            } else {
                if (!recipeIngredientsDTOS.contains(recipeIngredientsDTO)) {
                    recipeIngredientsDTOS.add(recipeIngredientsDTO);
                }
            }

        }

        return recipeIngredientsDTOS;
    }

    private boolean isIngredientAddedBefore(RecipeIngredientsDTO recipeIngredientsDTO, RecipeIngredientMatrix inner) {
        for (IngredientDTO ingredientDTO : recipeIngredientsDTO.getIngredients()) {
            if (ingredientDTO.getIngredientName().equals(inner.getIngredient().getIngredientName())) {
                return true;
            }
        }
        return false;
    }

    private RecipeIngredientsDTO findRecipeIngredientsDTO(List<RecipeIngredientsDTO> recipeIngredientsDTOS, RecipeIngredientMatrix outer) {
        RecipeIngredientsDTO recipeIngredientsDTO = new RecipeIngredientsDTO();
        recipeIngredientsDTO.setRecipeId(outer.getRecipe().getRecipeId());
        recipeIngredientsDTO.setRecipeName(outer.getRecipe().getRecipeName());
        for (RecipeIngredientsDTO dto : recipeIngredientsDTOS) {
            if (dto.getRecipeId().equals(outer.getRecipe().getRecipeId())) {
                recipeIngredientsDTO = dto;
                break;
            }
        }
        return recipeIngredientsDTO;
    }

}
