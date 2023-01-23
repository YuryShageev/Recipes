package me.shageyev.recipes.controllers;

import me.shageyev.recipes.services.IngredientsService;
import me.shageyev.recipes.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    public IngredientController(IngredientsService ingredientsService, RecipeService recipeService) {
        this.ingredientsService = ingredientsService;
        this.recipeService = recipeService;
    }

    public final IngredientsService ingredientsService;
    public final RecipeService recipeService;

    @GetMapping("/add")
    public String addIngredient(@RequestParam String name, @RequestParam int quantity, @RequestParam String measuringUnit) {
        ingredientsService.addIngredient(name, quantity, measuringUnit);
        return "Вы добавили " + ingredientsService.getAllIngredients();

    }

    @GetMapping()
    public String getIngredients(@RequestParam int id) {

        return "Вот какой ингредиент вы запросили " + ingredientsService.getIngredients(id);
    }

    @GetMapping("/all")
    public String getIngredients() {

        return "Вот какой ингредиент вы запросили " + ingredientsService.getAllIngredients();
    }
}
