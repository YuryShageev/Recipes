package me.shageyev.recipes.controllers;

import lombok.RequiredArgsConstructor;
import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.services.IngredientsService;
import me.shageyev.recipes.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    public IngredientController(IngredientsService ingredientsService, RecipeService recipeService) {
        this.ingredientsService = ingredientsService;
        this.recipeService = recipeService;
    }

    public final IngredientsService ingredientsService;
    public final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredients ingredients) {
        int id = ingredientsService.addIngredient(ingredients);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredients> getIngredientsById(@PathVariable int id) {
        Ingredients ingredients = ingredientsService.getIngredients(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/all")
    public String getIngredients() {

        return "Вот какой ингредиент вы запросили " + ingredientsService.getAllIngredients();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredients> editIngredients(@PathVariable int id, @RequestBody Ingredients ingredients) {
        Ingredients ingredients1 = ingredientsService.editIngredientsById(id, ingredients);
        if (ingredients1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientsService.deleteIngredientsById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
