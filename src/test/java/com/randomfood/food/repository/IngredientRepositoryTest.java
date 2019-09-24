package com.randomfood.food.repository;

import com.randomfood.food.TestConstants;
import com.randomfood.food.modal.Ingredient;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
//@ActiveProfiles("test")
class IngredientRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private IngredientRepository mockIngredientRepository;

    @BeforeEach
    void setUp() {
        Ingredient ingredient = new Ingredient.IngredientBuilder()
                .setIngredientId(TestConstants.ingredient_id)
                .setIngredientName(TestConstants.ingredient_name)
                .build();
        testEntityManager.merge(ingredient);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByIngredientId() {
        Optional<Ingredient> ingredient = mockIngredientRepository.findByIngredientId(TestConstants.ingredient_id);

        Assert.assertTrue(ingredient.isPresent());
        Assert.assertEquals(ingredient.get().getIngredientId(), TestConstants.ingredient_id);
        Assert.assertEquals(ingredient.get().getIngredientName(), TestConstants.ingredient_name);
    }
}