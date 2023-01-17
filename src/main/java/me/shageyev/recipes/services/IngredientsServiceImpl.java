package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;

import java.util.Map;
import java.util.TreeMap;

public class IngredientsServiceImpl implements IngredientsService{

    private static final Map<Long, Ingredients> ingredient = new TreeMap<>();

    private static long ingredientId = 0;


    @Override
    public void addIngredients(Ingredients ingredients) {
        ingredient.put(ingredientId++, ingredients);
    }

    @Override
    public void getIngredients(long ingredientId) {
        System.out.println(ingredient.get(ingredientId));
    }
}
