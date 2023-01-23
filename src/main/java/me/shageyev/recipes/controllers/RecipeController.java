package me.shageyev.recipes.controllers;


import me.shageyev.recipes.model.Recipe;
import me.shageyev.recipes.services.IngredientsService;
import me.shageyev.recipes.services.RecipeService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
    public final IngredientsService ingredientsService;
    public final RecipeService recipeService;


    public RecipeController(IngredientsService ingredientsService, RecipeService recipeService) {
        this.ingredientsService = ingredientsService;
        this.recipeService = recipeService;
    }



    @GetMapping("/add")
    public String addRecipe(@RequestParam String name, @RequestParam int cookingTime) {
        //Надо грамотно сделать добавление рецепта - здесь остановился
        recipeService.addRecipe(name, cookingTime);
        return "Вы добавили рецепт " + recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }



}
