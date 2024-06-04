package com.yaksha.training.recipe.functional;

import com.yaksha.training.recipe.controller.RecipeController;
import com.yaksha.training.recipe.entity.Recipe;
import com.yaksha.training.recipe.entity.RecipeStatus;
import com.yaksha.training.recipe.service.RecipeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.recipe.utils.MasterData.asJsonString;
import static com.yaksha.training.recipe.utils.MasterData.getRecipe;
import static com.yaksha.training.recipe.utils.MasterData.getRecipeList;
import static com.yaksha.training.recipe.utils.MasterData.randomBoolean;
import static com.yaksha.training.recipe.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.recipe.utils.TestUtils.businessTestFile;
import static com.yaksha.training.recipe.utils.TestUtils.currentTest;
import static com.yaksha.training.recipe.utils.TestUtils.testReport;
import static com.yaksha.training.recipe.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerListRecipesHome() throws Exception {
        List<Recipe> expected = getRecipeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Recipe> recipePage = new PageImpl<>(expected);
        when(recipeService.getRecipes(pageable)).thenReturn(recipePage);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-recipes")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("recipes")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerListRecipes() throws Exception {
        List<Recipe> expected = getRecipeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Recipe> recipePage = new PageImpl<>(expected);
        when(recipeService.getRecipes(pageable)).thenReturn(recipePage);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-recipes")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("recipes")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/addRecipeForm")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-recipe-form"), businessTestFile);
    }

    @Test
    public void testControllerSaveRecipe() throws Exception {
        Recipe recipe = getRecipe();
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/recipe/list"), businessTestFile);
    }

    @Test
    public void testControllerSaveRecipeHasError() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setId(null);
        recipe.setTitle(null);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-recipe-form"), businessTestFile);
    }

    @Test
    public void testControllerUpdateRecipeHasError() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setDescription(null);
        MvcResult result = this.mockMvc.perform(post("/saveRecipe")
                .flashAttr("recipe", recipe)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-recipe-form"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Recipe recipe = getRecipe();
        when(recipeService.getRecipe(recipe.getId())).thenReturn(recipe);
        MvcResult result = this.mockMvc.perform(get("/updateRecipeForm")
                .param("recipeId", recipe.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-recipe-form"), businessTestFile);
    }

    @Test
    public void testControllerDeleteRecipe() throws Exception {
        Recipe recipe = getRecipe();
        MvcResult result = this.mockMvc.perform(get("/delete")
                .param("recipeId", recipe.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/recipe/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchRecipes() throws Exception {
        String key = randomStringWithSize(2);
        List<Recipe> expected = getRecipeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Recipe> recipePage = new PageImpl<>(expected);
        when(recipeService.searchRecipes(key, pageable)).thenReturn(recipePage);
        MvcResult result = this.mockMvc.perform(post("/search")
                .param("theSearchName", key)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-recipes")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("recipes")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

    @Test
    public void testControllerSearchRecipesAsNullKey() throws Exception {
        List<Recipe> expected = getRecipeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Recipe> recipePage = new PageImpl<>(expected);
        when(recipeService.searchRecipes(null, pageable)).thenReturn(recipePage);
        MvcResult result = this.mockMvc.perform(post("/search")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-recipes")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("recipes")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

    @Test
    public void testControllerUpdateStatus() throws Exception {
        Recipe recipe = getRecipe();
        String status = randomBoolean() ? RecipeStatus.APPROVED.toString() : RecipeStatus.REJECTED.toString();

        MvcResult result = this.mockMvc.perform(get("/updateStatus")
                        .param("status", status)
                        .param("id", recipe.getId().toString()))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/recipe/list"), businessTestFile);
    }
}
