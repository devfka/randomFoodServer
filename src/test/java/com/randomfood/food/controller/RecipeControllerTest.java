package com.randomfood.food.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randomfood.food.RandomFoodApplication;
import com.randomfood.food.TestConstants;
import com.randomfood.food.modal.Ingredient;
import com.randomfood.food.modal.Recipe;
import com.randomfood.food.types.RecipeIngredientsDTO;
import com.randomfood.food.utils.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RandomFoodApplication.class)
@ActiveProfiles("test")
class RecipeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Recipe recipe;

    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .build();

        recipe = new Recipe.RecipeBuilder()
                .setRecipeId(TestConstants.recipe_id)
                .setRecipeName(TestConstants.recipe_name)
                .build();

        ingredient = new Ingredient.IngredientBuilder()
                .setIngredientName(TestConstants.ingredient_name)
                .setIngredientId(TestConstants.ingredient_id)
                .build();

        MockitoAnnotations.initMocks(this);
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(roles = Constants.USER_ROLE)
    void getRecipes2() throws Exception {

        mockMvc.perform(get("/getRecipes/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].recipeId", is(1)))
                .andExpect(jsonPath("$[0].recipeName", is("sucuklu kuru fasulye")))
                .andDo(print())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = Constants.ADMIN_ROLE)
    void getRecipes3() throws Exception {

        mockMvc.perform(get("/getRecipes/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].recipeId", is(1)))
                .andExpect(jsonPath("$[0].recipeName", is("sucuklu kuru fasulye")))
                .andDo(print())
                .andReturn();
    }

    @Test
    void getRecipes4() throws Exception {

        mockMvc.perform(get("/getRecipes/"))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = "Unknown")
    void getRecipes5() throws Exception {

        mockMvc.perform(get("/getRecipes/"))
                .andExpect(status().isForbidden()) //we can improve this as returning unauthorized error.
                .andDo(print())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = Constants.ADMIN_ROLE)
    void addRecipe() throws Exception {

        RecipeIngredientsDTO recipeIngredientsDTO = new RecipeIngredientsDTO(recipe, Arrays.asList(ingredient));

        mockMvc.perform(post("/addRecipe/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeIngredientsDTO)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

    }


    @Test
    @WithMockUser(roles = Constants.ADMIN_ROLE)
    void addRecipe2() throws Exception {

        //BAD REQUEST

        mockMvc.perform(post("/addRecipe/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = Constants.USER_ROLE)
    void addRecipe3() throws Exception {

        //UnAuthorized

        RecipeIngredientsDTO recipeIngredientsDTO = new RecipeIngredientsDTO(recipe, Arrays.asList(ingredient));

        mockMvc.perform(post("/addRecipe/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeIngredientsDTO)))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(roles = Constants.ADMIN_ROLE)
    void deleteRecipe() throws Exception {
        mockMvc.perform(get("/deleteRecipe/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        mockMvc.perform(get("/getRecipes/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = Constants.ADMIN_ROLE)
    void deleteRecipe2() throws Exception {

        //Entity Not Found Exception

        mockMvc.perform(get("/deleteRecipe/{id}", 256L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();
    }

    @Test
    void deleteRecipe3() throws Exception {
        mockMvc.perform(get("/deleteRecipe/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andReturn();

    }

    @Test
    @WithMockUser(roles = Constants.USER_ROLE)
    void deleteRecipe4() throws Exception {
        mockMvc.perform(get("/deleteRecipe/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}