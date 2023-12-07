package com.yaksha.training.recipe.service;

import com.yaksha.training.recipe.entity.Recipe;
import com.yaksha.training.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id).get();
    }

    public boolean deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
        return true;
    }

    public List<Recipe> searchRecipes(String theSearchName) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return recipeRepository.findByTitle(theSearchName);
        } else {
            return recipeRepository.findAll();
        }
    }
}
