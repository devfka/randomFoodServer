package com.randomfood.food.modal;

import com.randomfood.food.TestConstants;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTest {

    private Recipe recipeFirst;

    private Recipe recipeSecond;

    @BeforeEach
    void setUp() {
        recipeFirst = new Recipe.RecipeBuilder()
                .setRecipeId(TestConstants.recipe_id)
                .setRecipeName(TestConstants.recipe_name)
                .build();

        recipeSecond = new Recipe.RecipeBuilder()
                .setRecipeId(TestConstants.recipe_id)
                .setRecipeName(TestConstants.recipe_name)
                .build();
    }

    @AfterEach
    void tearDown() {
        recipeFirst = null;
    }

    @Test
    void beanIsSerializable() {
        final byte[] serializedMyBean = SerializationUtils.serialize(recipeFirst);
        final Recipe deserializedMyBean = (Recipe) SerializationUtils.deserialize(serializedMyBean);
        assertEquals(recipeFirst, deserializedMyBean);
    }

    @Test
    void testEquals() {
        assertEquals(recipeFirst, recipeSecond);
    }

    @Test
    void testNotEquals() {
        recipeSecond.setRecipeId(3L);
        assertNotEquals(recipeFirst, recipeSecond);
    }

    @Test
    void testHashCodeEquals() {
        assertEquals(recipeFirst.hashCode(), recipeSecond.hashCode());
    }

    @Test
    void testHashCodeNotEqual() {
        recipeSecond.setRecipeId(3L);
        assertNotEquals(recipeFirst.hashCode(), recipeSecond.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(recipeFirst.toString(), "Recipe{" + "recipeId=" + recipeFirst.getRecipeId() + ", name='" + recipeFirst.getRecipeName() + '\'' + '}');
    }

    @Test
    void testToStringNegative() {
        assertNotEquals(recipeFirst.toString(), "wrong message");
    }

    @Test
    void getRecipeId() {
        Assert.assertEquals(recipeFirst.getRecipeId(), TestConstants.recipe_id);
    }

    @Test
    void setRecipeId() {
        recipeFirst.setRecipeId(4L);
        Assert.assertEquals(recipeFirst.getRecipeId(), new Long(4));
    }

    @Test
    void getRecipeName() {
        Assert.assertEquals(recipeFirst.getRecipeName(), TestConstants.recipe_name);
    }

    @Test
    void setRecipeName() {
        recipeFirst.setRecipeName("recipe test name");
        Assert.assertEquals(recipeFirst.getRecipeName(), "recipe test name");
    }

    @Test
    void compareToEquals() {
        recipeSecond.setRecipeId(1L);
        assertEquals(recipeFirst.compareTo(recipeSecond), 0);
    }

    @Test
    void compareToBigger() {
        recipeSecond.setRecipeId(2L);
        assertEquals(recipeFirst.compareTo(recipeSecond), 1);
    }

    @Test
    void compareToLess() {
        recipeSecond.setRecipeId(0L);
        assertEquals(recipeFirst.compareTo(recipeSecond), -1);
    }

}