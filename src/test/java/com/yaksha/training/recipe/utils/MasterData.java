package com.yaksha.training.recipe.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.training.recipe.entity.Recipe;
import com.yaksha.training.recipe.entity.RecipeStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    private static Random rnd = new Random();

    public static Recipe getRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setTitle(randomStringWithSize(10));
        recipe.setDescription(randomStringWithSize(10));
        recipe.setIngredients(randomStringWithSize(10));
        recipe.setCookTime(randomNumberWithSize(1));
        recipe.setStatus(RecipeStatus.PENDING);
        return recipe;
    }

    public static List<Recipe> getRecipeList(int size) {
        Long id = 0L;
        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Recipe recipe = new Recipe();
            recipe.setId(++id);
            recipe.setTitle(randomStringWithSize(10));
            recipe.setDescription(randomStringWithSize(10));
            recipe.setIngredients(randomStringWithSize(10));
            recipe.setCookTime(randomNumberWithSize(1));
            recipe.setStatus(RecipeStatus.PENDING);
            recipes.add(recipe);
        }
        return recipes;
    }


    public static Integer randomNumberWithSize(int size) {
        String alphabet = "123456789";
        return rnd.nextInt(alphabet.length());
    }

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
