package com.randomfood.food.service.impl;

import com.randomfood.food.mapper.IngredientMapper;
import com.randomfood.food.modal.Ingredient;
import com.randomfood.food.repository.IngredientRepository;
import com.randomfood.food.service.IngredientService;
import com.randomfood.food.types.IngredientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientMapper ingredientMapper;

    @Override
    public List<IngredientDTO> findAll() {
        return ingredientMapper.ingredientsToIngredientDTOs(ingredientRepository.findAll());
    }

    @Override
    public Ingredient findByIngredientId(Long ingredientId) {
        return this.ingredientRepository.findByIngredientId(ingredientId);
    }

    @Override
    public Ingredient saveIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientDTO.getIngredientName());
        return this.ingredientRepository.save(ingredient);
    }
}

