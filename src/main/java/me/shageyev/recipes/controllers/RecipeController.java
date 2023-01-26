package me.shageyev.recipes.controllers;


import me.shageyev.recipes.model.Recipe;
import me.shageyev.recipes.services.IngredientsService;
import me.shageyev.recipes.services.RecipeService;

import org.springframework.http.ResponseEntity;
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


    @PostMapping("/add")
    Recipe addRecipe(@RequestBody Recipe recipe) {

        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipeById(id, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
