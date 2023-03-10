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
import me.shageyev.recipes.services.IngredientsService;
import me.shageyev.recipes.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-операции для работы с ингредиентами приложения")
public class IngredientController {

    public IngredientController(IngredientsService ingredientsService, RecipeService recipeService) {
        this.ingredientsService = ingredientsService;
        this.recipeService = recipeService;
    }

    public final IngredientsService ingredientsService;
    public final RecipeService recipeService;

    @PostMapping
    @Operation(
            summary = "Добавление ингредиента",
            description = "Важно внести три параметра!"
    )
    @Parameters(value = {
            @Parameter(name = "productName", example = "Лук"),
            @Parameter(name = "amount", example = "2"),
            @Parameter(name = "measuringUnit", example = "шт")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были добавлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredients.class))
                            )
                    }
            )
    })
    public Ingredients addIngredient(@RequestBody Ingredients ingredients) {
        return ingredientsService.addIngredient(ingredients);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск ингредиента",
            description = "Важно внести указать ключ/номер ингредиента!"
    )@Parameters(value = {
            @Parameter(example = "2"),
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredients.class))
                            )
                    }
            )
    })
    public ResponseEntity<Ingredients> getIngredientsById(@PathVariable int id) {
        Ingredients ingredients = ingredientsService.getIngredients(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Вывод списка всех добавленных ингредиентов",
            description = "Вносить ничего не нужно"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredients.class))
                            )
                    }
            )
    })
    public Collection<Ingredients> getAllIngredients() {

        return ingredientsService.getAllIngredients();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингредиента",
            description = "Важно внести указать ключ/номер ингредиента, а затем изменить их!"
    )@Parameters(value = {
            @Parameter(name = "productName", example = "Яйцо"),
            @Parameter(name = "amount", example = "5"),
            @Parameter(name = "measuringUnit", example = "шт")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент исправлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredients.class))
                            )
                    }
            )
    })
    public ResponseEntity<Ingredients> editIngredients(@PathVariable int id, @RequestBody Ingredients ingredients) {
        Ingredients ingredients1 = ingredientsService.editIngredientsById(id, ingredients);
        if (ingredients1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients1);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента",
            description = "Нужно внести ключ/номер ингредиента"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент удалён",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredients.class))
                            )
                    }
            )
    })
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientsService.deleteIngredientsById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
