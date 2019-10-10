package com.randomfood.food.service.impl;

import com.randomfood.food.RandomFoodApplication;
import com.randomfood.food.TestConstants;
import com.randomfood.food.mapper.RecipeMapper;
import com.randomfood.food.modal.Recipe;
import com.randomfood.food.repository.RecipeRepository;
import com.randomfood.food.types.RecipeDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RandomFoodApplication.class)
@ActiveProfiles("test")
class RecipeServiceImplTest {

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private RecipeMapper recipeMapper;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    private Recipe recipe = null;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipe = new Recipe.RecipeBuilder()
                .setRecipeId(TestConstants.recipe_id)
                .setRecipeName(TestConstants.recipe_name)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByRecipeName() {
        when(recipeRepository.findByRecipeName(TestConstants.recipe_name)).thenReturn(Arrays.asList(recipe));

        List<Recipe> recipeList = recipeService.findByRecipeName(TestConstants.recipe_name);

        assertEquals(recipeList.size(), 1);
        assertEquals(recipeList.get(0).getRecipeName(), recipe.getRecipeName());
        assertEquals(recipeList.get(0).getRecipeId(), recipe.getRecipeId());
        verify(recipeRepository, times(1)).findByRecipeName(TestConstants.recipe_name);
    }

    @Test
    void findByRecipeName2() {
        when(recipeRepository.findByRecipeName("unknown")).thenReturn(null);

        List<Recipe> recipeList = recipeService.findByRecipeName("unknown");

        assertNull(recipeList);
        verify(recipeRepository, times(1)).findByRecipeName("unknown");
    }

    @Test
    void findByRecipeId() {
        when(recipeRepository.findByRecipeId(TestConstants.recipe_id)).thenReturn(Optional.ofNullable(recipe));

        Optional<Recipe> result = recipeService.findByRecipeId(TestConstants.recipe_id);

        assertTrue(result.isPresent());
        assertEquals(result.get().getRecipeName(), recipe.getRecipeName());
        assertEquals(result.get().getRecipeId(), recipe.getRecipeId());
        verify(recipeRepository, times(1)).findByRecipeId(TestConstants.recipe_id);
    }

    @Test
    void findByRecipeId2() {
        when(recipeRepository.findByRecipeId(TestConstants.recipe_id)).thenReturn(Optional.empty());

        Optional<Recipe> result = recipeService.findByRecipeId(TestConstants.recipe_id);

        assertFalse(result.isPresent());
        verify(recipeRepository, times(1)).findByRecipeId(TestConstants.recipe_id);
    }

    @Test
    void findAll() {
        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe));
        when(recipeMapper.recipesToRecipeDTOs(Arrays.asList(recipe))).thenReturn(Arrays.asList(new RecipeDTO(recipe)));

        List<RecipeDTO> recipeList = recipeService.findAll();

        assertEquals(recipeList.size(), 1);
        assertEquals(recipeList.get(0).getRecipeName(), recipe.getRecipeName());
        assertEquals(recipeList.get(0).getRecipeId(), recipe.getRecipeId());
        verify(recipeRepository, times(1)).findAll();
    }


    @Test
    void save() {
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        Recipe result = recipeService.save(recipe);

        verify(recipeRepository, times(1)).save(result);
    }

//    @Test(expected = DataIntegrityViolationException.class)
//    public void saveTaskReturnException() throws Exception {
//        when(recipeRepository.save(recipe)).thenThrow(new DataIntegrityViolationException("exception"));
//
//        recipeService.save(recipe);
//
//    }
}