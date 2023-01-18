package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private IngredientsService ingredientsService;

    private static final Map<Integer, Recipe> recipeMap = new TreeMap<>();
    private static Integer recipeId = 0;
    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(recipeId++, recipe);
        System.out.println(recipeMap);
    }

    @Override
    public Recipe getRecipe(int recipeId) {
        if (recipeMap.get(recipeId) == null) {
            throw new IllegalArgumentException("Рецепт ещё не добавлен");
        } else {
            return recipeMap.get(recipeId);
        }
    }

    @Override
    public Map getAllRecipes() {
        Map<Integer, Recipe> integerRecipeMap;
        integerRecipeMap = recipeMap;
        return integerRecipeMap;
    }
}
