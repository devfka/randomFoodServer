package com.randomfood.food.mapper;

import com.randomfood.food.modal.Ingredient;
import com.randomfood.food.types.IngredientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IngredientMapper {
    private final Logger log = LoggerFactory.getLogger(IngredientMapper.class);

    public IngredientDTO ingredientToIngredientDTO(Ingredient ingredient) {
        return new IngredientDTO(ingredient);
    }

    public List<IngredientDTO> ingredientsToIngredientDTOs(List<Ingredient> ingredients) {
        return ingredients.stream().filter(Objects::nonNull)
                .map(this::ingredientToIngredientDTO)
                .collect(Collectors.toList());
    }

}
