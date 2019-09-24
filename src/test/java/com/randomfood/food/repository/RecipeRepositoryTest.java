package com.randomfood.food.repository;

import com.randomfood.food.TestConstants;
import com.randomfood.food.modal.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
//@ActiveProfiles("test")
class RecipeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RecipeRepository mockRecipeRepository;

    @BeforeEach
    void setUp() {
        Recipe recipe = new Recipe.RecipeBuilder()
                .setRecipeId(TestConstants.recipe_id)
                .setRecipeName(TestConstants.recipe_name)
                .build();
        testEntityManager.merge(recipe);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByRecipeName() {
        List<Recipe> recipeList = mockRecipeRepository.findByRecipeName(TestConstants.recipe_name);

        Assert.assertNotNull(recipeList);
        Assert.assertEquals(recipeList.get(0).getRecipeName(), TestConstants.recipe_name);
        Assert.assertEquals(recipeList.get(0).getRecipeId(), TestConstants.recipe_id);
    }

    @Test
    void findByRecipeNameNegativeTest() {
        List<Recipe> recipeList = mockRecipeRepository.findByRecipeName(StringUtils.EMPTY);

        Assert.assertEquals(recipeList.size(), 0);
    }

    @Test
    void findByRecipeId() {
        Optional<Recipe> recipe = mockRecipeRepository.findByRecipeId(TestConstants.recipe_id);

        Assert.assertTrue(recipe.isPresent());
        Assert.assertEquals(recipe.get().getRecipeId(), TestConstants.recipe_id);
        Assert.assertEquals(recipe.get().getRecipeName(), TestConstants.recipe_name);
    }

    @Test
    void findByRecipeIdNegativeTest() {
        Optional<Recipe> recipe = mockRecipeRepository.findByRecipeId(123L);

        Assert.assertFalse(recipe.isPresent());
    }
}