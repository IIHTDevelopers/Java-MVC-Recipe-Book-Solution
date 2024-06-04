package com.yaksha.training.recipe.service;

import com.yaksha.training.recipe.entity.Recipe;
import com.yaksha.training.recipe.entity.RecipeStatus;
import com.yaksha.training.recipe.repository.RecipeRepository;
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

import java.util.List;
import java.util.Optional;

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

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetRecipes() throws Exception {
        List<Recipe> actual = getRecipeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Recipe> recipePage = new PageImpl<>(actual);
        when(recipeRepository.findAllRecipe(pageable)).thenReturn(recipePage);
        Page<Recipe> expected = recipeService.getRecipes(pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveRecipe() throws Exception {
        Recipe actual = getRecipe();
        when(recipeRepository.save(actual)).thenReturn(actual);
        Recipe expected = recipeService.saveRecipe(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetRecipe() throws Exception {
        Recipe actual = getRecipe();
        when(recipeRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Recipe expected = recipeService.getRecipe(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceDeleteRecipe() throws Exception {
        Recipe actual = getRecipe();
        boolean expected = recipeService.deleteRecipe(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

    @Test
    public void testServiceSearchRecipeWithNull() throws Exception {
        List<Recipe> actual = getRecipeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Recipe> recipePage = new PageImpl<>(actual);
        when(recipeRepository.findAllRecipe(pageable)).thenReturn(recipePage);
        Page<Recipe> expected = recipeService.searchRecipes(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchRecipeWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        List<Recipe> actual = getRecipeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Recipe> recipePage = new PageImpl<>(actual);
        when(recipeRepository.findByRecipeByTitleOrDesc(searchKey, pageable)).thenReturn(recipePage);
        Page<Recipe> expected = recipeService.searchRecipes(searchKey, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testControllerUpdateStatus() throws Exception {
        Recipe recipe = getRecipe();
        String status = randomBoolean() ? RecipeStatus.APPROVED.toString() : RecipeStatus.REJECTED.toString();

        boolean expected = recipeService.updateRecipeStatus(recipe.getId(), status);
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }
}
