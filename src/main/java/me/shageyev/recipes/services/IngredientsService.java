package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;

import java.util.List;

public interface IngredientsService {


    void addIngredient(String name, int quantity, String measuringUnit);

//    void addIngredients(String name, int quantity, String measuringUnit);

    Ingredients getIngredients(int ingredientId);

    List<Ingredients> getAllIngredients();
}
