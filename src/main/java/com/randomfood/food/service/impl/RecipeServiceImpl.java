package com.randomfood.food.service.impl;

import com.randomfood.food.mapper.RecipeMapper;
import com.randomfood.food.modal.Recipe;
import com.randomfood.food.repository.RecipeRepository;
import com.randomfood.food.service.RecipeService;
import com.randomfood.food.types.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    @Override
    public List<Recipe> findByRecipeName(String name) {
        return recipeRepository.findByRecipeName(name);
    }

    @Override
    public List<RecipeDTO> findAll() {
        return recipeMapper.recipesToRecipeDTOs(recipeRepository.findAll());
    }

    @Override
    public Optional<Recipe> findByRecipeId(Long recipeId) {
        return recipeRepository.findByRecipeId(recipeId);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}

