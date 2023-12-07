package com.yaksha.training.recipe.boundary;


import com.yaksha.training.recipe.entity.Recipe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.recipe.utils.MasterData.getRecipe;
import static com.yaksha.training.recipe.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.recipe.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.recipe.utils.TestUtils.currentTest;
import static com.yaksha.training.recipe.utils.TestUtils.testReport;
import static com.yaksha.training.recipe.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }


    @Test
    public void testRecipeTitleNotBlank() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setTitle("");
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeTitleNotNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setTitle(null);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeTitleMinTwo() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setTitle(randomStringWithSize(1));
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeTitleMaxForty() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setTitle(randomStringWithSize(41));
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeDescriptionNotBlank() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setDescription("");
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeDescriptionNotNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setDescription(null);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeDescriptionMinTwo() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setDescription(randomStringWithSize(1));
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeDescriptionMaxTwoHundred() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setDescription(randomStringWithSize(201));
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeIngredientsNotBlank() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setIngredients("");
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeIngredientsNotNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setIngredients(null);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRecipeCookTimeNotNull() throws Exception {
        Recipe recipe = getRecipe();
        recipe.setCookTime(null);
        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
