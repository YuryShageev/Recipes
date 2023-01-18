package me.shageyev.recipes.controllers;

import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;
import me.shageyev.recipes.services.IngredientsService;
import me.shageyev.recipes.services.RecipeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
    public final IngredientsService ingredientsService;
    public final RecipeService recipeService;
    private static final List<Ingredients> ingredientList = new ArrayList<>();
    private static final List<String> cookingSteps = new ArrayList<>();

    public RecipeController(IngredientsService ingredientsService, RecipeService recipeService) {
        this.ingredientsService = ingredientsService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ingredients/add")
    public String addIngredient(@RequestParam String name, @RequestParam int quantity, @RequestParam String measuringUnit) {
        Ingredients ingredients = new Ingredients(name, quantity, measuringUnit);
        ingredientList.add(ingredients);
        ingredientsService.addIngredients(name, quantity, measuringUnit);
        return "Вы добавили " + ingredientsService.getAllIngredients();

    }

    @GetMapping("/borsch/add")
    public String addRecipe(@RequestParam String name, @RequestParam int cookingTime) {
        //Надо грамотно сделать добавление рецепта - здесь остановился
        String cookSteps = "Здесь временно ничего нет";
        cookingSteps.add(cookSteps);
        Recipe recipe = new Recipe(name, cookingTime, ingredientList, cookingSteps);
        recipeService.addRecipe(recipe);
        return "Вы добавили рецепт " + recipeService.getAllRecipes();
    }

    @GetMapping("/borsch")
    public Recipe getRecipe(@RequestParam int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("/ingredients")
    public String getIngredients(@RequestParam int id) {

        return "Вот какой ингредиент вы запросили " + ingredientsService.getIngredients(id);
    }
}
