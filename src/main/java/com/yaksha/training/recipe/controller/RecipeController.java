package com.yaksha.training.recipe.controller;

import com.yaksha.training.recipe.entity.Recipe;
import com.yaksha.training.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = {"/recipe", "/"})
public class RecipeController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = {"/list", "/"})
    public String listRecipes(Model model) {
        List<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);
        return "list-recipes";
    }

    @GetMapping("/addRecipeForm")
    public String showFormForAdd(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "add-recipe-form";
    }

    @PostMapping("/saveRecipe")
    public String saveRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (recipe.getId() != null) {
                return "update-recipe-form";
            }
            return "add-recipe-form";
        } else {
            recipeService.saveRecipe(recipe);
            return "redirect:/recipe/list";
        }
    }

    @GetMapping("/updateRecipeForm")
    public String showFormForUpdate(@RequestParam("recipeId") Long id, Model model) {
        Recipe recipe = recipeService.getRecipe(id);
        model.addAttribute("recipe", recipe);
        return "update-recipe-form";
    }

    @GetMapping("/delete")
    public String deleteRecipe(@RequestParam("recipeId") Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipe/list";
    }

    @PostMapping("/search")
    public String searchRecipes(@RequestParam("theSearchName") String theSearchName,
                                Model theModel) {

        List<Recipe> theRecipes = recipeService.searchRecipes(theSearchName);
        theModel.addAttribute("recipes", theRecipes);
        return "list-recipes";
    }
}
