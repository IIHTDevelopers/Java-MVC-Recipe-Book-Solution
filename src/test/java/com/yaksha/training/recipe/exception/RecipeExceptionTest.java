package com.yaksha.training.recipe.exception;

import com.yaksha.training.recipe.controller.RecipeController;
import com.yaksha.training.recipe.entity.Recipe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.recipe.utils.MasterData.getRecipe;
import static com.yaksha.training.recipe.utils.TestUtils.currentTest;
import static com.yaksha.training.recipe.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.recipe.utils.TestUtils.testReport;
import static com.yaksha.training.recipe.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RecipeExceptionTest {

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSaveRecipeTitleAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setId(null);
        recipe.setTitle(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-recipe-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveRecipeDescAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setId(null);
        recipe.setDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-recipe-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveRecipeIngredientsAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setId(null);
        recipe.setIngredients(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-recipe-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveRecipeCookTimeAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setId(null);
        recipe.setCookTime(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-recipe-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateRecipeTitleAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setTitle(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-recipe-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateRecipeDescAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-recipe-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateRecipeIngredientsAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setIngredients(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-recipe-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateRecipeCookTimeAsNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setCookTime(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-recipe-form")), exceptionTestFile);
    }

}