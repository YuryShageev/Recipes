package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Map<Integer, Recipe> recipeMap = new TreeMap<>();
    private static final List<Ingredients> ingredientsList = new ArrayList<>();
    private static final List<String> cookingSteps = new ArrayList<>();
    private static Integer recipeId = 0;
    @Override
    public void addRecipe(String name, int cookingTime) {
        String cookSteps = "Здесь временно ничего нет";
        cookingSteps.add(cookSteps);

        Recipe recipe = new Recipe(name, cookingTime, ingredientsList, cookingSteps);
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
    public Map<Integer, Recipe> getAllRecipes() {

        return recipeMap;
    }

    @Override
    public void addIngredientsToRecipe(Ingredients ingredient) {
         ingredientsList.add(ingredient);
    }
}
