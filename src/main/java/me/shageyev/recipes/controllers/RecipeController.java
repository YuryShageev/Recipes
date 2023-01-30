package me.shageyev.recipes.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;
import me.shageyev.recipes.services.IngredientsService;
import me.shageyev.recipes.services.RecipeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции для работы с рецептами приложения")
public class RecipeController {
    public final IngredientsService ingredientsService;
    public final RecipeService recipeService;


    public RecipeController(IngredientsService ingredientsService, RecipeService recipeService) {
        this.ingredientsService = ingredientsService;
        this.recipeService = recipeService;
    }


    @PostMapping("/add")
    @Operation(
            summary = "Добавление рецепта",
            description = "Важно внести три параметра, список инредиентов и список шагов!"
    )
    @Parameters(value = {
            @Parameter(name = "name", example = "Хот-Дог"),
            @Parameter(name = "cookingTime", example = "10"),
            @Parameter(name = "measuringUnit", example = "мин"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были добавлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    Recipe addRecipe(@RequestBody Recipe recipe) {

        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта",
            description = "Важно внести номер/ключ рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта",
            description = "Важно внести номер/ключ рецепта и изменить три параметра, список инредиентов и список шагов!"
    )
    @Parameters(value = {
            @Parameter(name = "name", example = "Хот-Дог"),
            @Parameter(name = "cookingTime", example = "10"),
            @Parameter(name = "measuringUnit", example = "мин"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был изменён",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipeById(id, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта",
            description = "Важно внести номер/ключ рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удалён",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<Void> deleteRecipeById(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
