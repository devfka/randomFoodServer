package com.randomfood.food.service.impl;

import com.randomfood.food.exceptions.EntityNotFoundException;
import com.randomfood.food.modal.Recipe;
import com.randomfood.food.modal.RecipeIngredientMatrix;
import com.randomfood.food.repository.RecipeIngredientMatrixRepository;
import com.randomfood.food.service.IngredientService;
import com.randomfood.food.service.RecipeInredientMatrixService;
import com.randomfood.food.service.RecipeService;
import com.randomfood.food.types.IngredientDTO;
import com.randomfood.food.types.RecipeIngredientsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeIngredientMatrixServiceImpl implements RecipeInredientMatrixService {

    private final Logger log = LoggerFactory.getLogger(RecipeIngredientMatrixServiceImpl.class);

    @Autowired
    private RecipeIngredientMatrixRepository recipeIngredientMatrixRepository;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<RecipeIngredientMatrix> findByRecipeId(Long recipeId) throws EntityNotFoundException {
        List<RecipeIngredientMatrix> recipeIngredientMatrixList = this.recipeIngredientMatrixRepository.findByRecipe_RecipeId(recipeId);

        if (recipeIngredientMatrixList == null || recipeIngredientMatrixList.size() == 0) {
            log.error("An Error has been occurred : " + EntityNotFoundException.errorMessage);
            throw new EntityNotFoundException(EntityNotFoundException.errorMessage);
        }
        return recipeIngredientMatrixList;
    }

    @Override
    public List<RecipeIngredientMatrix> findByIngredientId(Long ingredientId) throws EntityNotFoundException {
        List<RecipeIngredientMatrix> recipeIngredientMatricexList = this.recipeIngredientMatrixRepository.findByIngredient_IngredientId(ingredientId);

        if (recipeIngredientMatricexList == null || recipeIngredientMatricexList.size() == 0) {
            log.error("An Error has been occurred : " + EntityNotFoundException.errorMessage);
            throw new EntityNotFoundException(EntityNotFoundException.errorMessage);
        }

        return recipeIngredientMatricexList;
    }


    @Override
    public List<RecipeIngredientMatrix> findAll() {
        return this.recipeIngredientMatrixRepository.findAll();
    }

    @Override
    public List<RecipeIngredientMatrix> findByIngredientInAndIngredientNotIn(List<Long> selectedIngredientIdList, List<Long> unselectedIngredientIdList) {
        Set<Long> recipeIdList = new HashSet<>();
        Set<Long> unSelectedrecipeIdList = new HashSet<>();
        List<RecipeIngredientMatrix> returnList = new ArrayList<>();

        if (selectedIngredientIdList != null && selectedIngredientIdList.size() > 0) {
            List<RecipeIngredientMatrix> selectedList = this.recipeIngredientMatrixRepository.findByIngredientIngredientIdIn(selectedIngredientIdList);
            if (unselectedIngredientIdList != null && unselectedIngredientIdList.size() > 0) {
                findUnselectedRecipeList(unselectedIngredientIdList, recipeIdList, unSelectedrecipeIdList, selectedList);
            } else {
                findRecipeList(recipeIdList, selectedList);
            }
        } else {
            List<RecipeIngredientMatrix> allRecipeIngredientMatrix = this.recipeIngredientMatrixRepository.findAll();
            if (unselectedIngredientIdList != null && unselectedIngredientIdList.size() > 0) {
                findUnselectedRecipeList(unselectedIngredientIdList, recipeIdList, unSelectedrecipeIdList, allRecipeIngredientMatrix);
            } else {
                findRecipeList(recipeIdList, allRecipeIngredientMatrix);
            }
        }

        for (Long recipeId : recipeIdList) {
            returnList.addAll(this.recipeIngredientMatrixRepository.findByRecipe_RecipeId(recipeId));
        }

        return returnList;
    }

    @Override
    @Transactional
    public RecipeIngredientMatrix save(RecipeIngredientMatrix recipeIngredientMatrix) {
        return this.recipeIngredientMatrixRepository.save(recipeIngredientMatrix);
    }

    @Override
    @Transactional
    public void saveRecipeAndIngredients(RecipeIngredientsDTO recipeIngredientsDTO) {
        Recipe recipe;
        if (recipeIngredientsDTO.isEditMode()) {
            List<Recipe> recipeList = this.recipeService.findByRecipeName(recipeIngredientsDTO.getRecipeName());
            if (recipeList != null && recipeList.size() > 0) {
                recipe = recipeList.get(0);
                this.recipeIngredientMatrixRepository.deleteByRecipeRecipeId(recipe.getRecipeId());
                prepareAndSaveRecipeIngrdients(recipeIngredientsDTO, recipe);
            }
        } else {
            recipe = new Recipe();
            recipe.setRecipeName(recipeIngredientsDTO.getRecipeName());
            this.recipeService.save(recipe);
            prepareAndSaveRecipeIngrdients(recipeIngredientsDTO, recipe);
        }
    }

    @Override
    @Transactional
    public void deleteRecipeByRecipeId(long recipeId) {
        this.recipeIngredientMatrixRepository.deleteByRecipeRecipeId(recipeId);
    }

    private void prepareAndSaveRecipeIngrdients(RecipeIngredientsDTO recipeIngredientsDTO, Recipe recipe) {
        if (recipeIngredientsDTO.getIngredients() != null && recipeIngredientsDTO.getIngredients().size() > 0) {
            for (IngredientDTO ingredientDTO : recipeIngredientsDTO.getIngredients()) {
                RecipeIngredientMatrix recipeIngredientMatrix = new RecipeIngredientMatrix();
                recipeIngredientMatrix.setOptional("N");
                recipeIngredientMatrix.setRecipe(recipe);
                recipeIngredientMatrix.setIngredient(this.ingredientService.findByIngredientId(ingredientDTO.getIngredientId()).isPresent() ? this.ingredientService.findByIngredientId(ingredientDTO.getIngredientId()).get() : null);
                this.save(recipeIngredientMatrix);
            }
        }
    }

    private void findUnselectedRecipeList(List<Long> unselectedIngredientIdList, Set<Long> recipeIdList, Set<Long> unSelectedrecipeIdList, List<RecipeIngredientMatrix> selectedList) {
        List<RecipeIngredientMatrix> unselectedList = this.recipeIngredientMatrixRepository.findByIngredientIngredientIdIn(unselectedIngredientIdList);
        findRecipeList(unSelectedrecipeIdList, unselectedList);
        compareRecipeLists(unSelectedrecipeIdList, recipeIdList, selectedList);
    }

    private void compareRecipeLists(Set<Long> unselectedIngredientIdList, Set<Long> recipeIdList, List<RecipeIngredientMatrix> allRecipeIngredientMatrix) {
        for (RecipeIngredientMatrix recipeIngredientMatrix : allRecipeIngredientMatrix) {
            if (!unselectedIngredientIdList.contains(recipeIngredientMatrix.getRecipe().getRecipeId())) {
                recipeIdList.add(recipeIngredientMatrix.getRecipe().getRecipeId());
            }
        }
    }

    private void findRecipeList(Set<Long> recipeIdList, List<RecipeIngredientMatrix> allRecipeIngredientMatrix) {
        for (RecipeIngredientMatrix recipeIngredientMatrix : allRecipeIngredientMatrix) {
            recipeIdList.add(recipeIngredientMatrix.getRecipe().getRecipeId());
        }
    }
}

