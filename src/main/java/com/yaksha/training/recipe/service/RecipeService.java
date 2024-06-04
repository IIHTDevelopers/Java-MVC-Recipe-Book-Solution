package com.yaksha.training.recipe.service;

import com.yaksha.training.recipe.entity.Recipe;
import com.yaksha.training.recipe.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Page<Recipe> getRecipes(Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findAllRecipe(pageable);
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

    public Page<Recipe> searchRecipes(String theSearchName, Pageable pageable) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return recipeRepository.findByRecipeByTitleOrDesc(theSearchName, pageable);
        } else {
            return recipeRepository.findAllRecipe(pageable);
        }
    }

    public boolean updateRecipeStatus(Long id, String status) {
        recipeRepository.updateRecipeStatus(id, status);
        return true;
    }
}
