package me.shageyev.recipes.controllers;

import me.shageyev.recipes.model.Recipe;
import me.shageyev.recipes.services.IngredientsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
public class RecipeController {
    IngredientsService ingredientsService;
    Map<Long, Recipe> borsch = new TreeMap<>();

    @GetMapping("/borsch")
    public Map getBorschRecipe(@RequestParam String name) {
        String nameCheck = "borsch";
        if (name.equals(nameCheck) && !name.isBlank()) {
            return borsch;

        } else {
            throw new IllegalArgumentException("К сожалению, такой рецепт отсутствует");
        }
    }

    @GetMapping("/borschIngredients")
    public void getIngredients(@RequestParam long id) {
        ingredientsService.getIngredients(id);
    }
}
