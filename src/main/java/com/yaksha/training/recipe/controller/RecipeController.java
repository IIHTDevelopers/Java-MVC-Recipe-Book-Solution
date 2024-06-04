package com.yaksha.training.recipe.controller;

import com.yaksha.training.recipe.entity.Recipe;
import com.yaksha.training.recipe.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String listRecipes(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Recipe> recipes = recipeService.getRecipes(pageable);
        model.addAttribute("recipes", recipes.getContent());
        model.addAttribute("theSearchName", "");
        model.addAttribute("totalPage", recipes.getTotalPages());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
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

    @RequestMapping("/search")
    public String searchRecipes(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                                @PageableDefault(size = 5) Pageable pageable,
                                Model theModel) {

        Page<Recipe> theRecipes = recipeService.searchRecipes(theSearchName, pageable);
        theModel.addAttribute("recipes", theRecipes.getContent());
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("totalPage", theRecipes.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-recipes";
    }

    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam("id") Long id,
                               @RequestParam("status") String status) {
        recipeService.updateRecipeStatus(id, status);
        return "redirect:/recipe/list";
    }
}
