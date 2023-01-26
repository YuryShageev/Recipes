package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;

import java.util.List;

public interface IngredientsService {



//    void addIngredients(String name, int quantity, String measuringUnit);

    int addIngredient(Ingredients ingredients);

    Ingredients getIngredients(int ingredientId);

    List<Ingredients> getAllIngredients();


    Ingredients editIngredientsById(int id, Ingredients ingredients);

    boolean deleteIngredientsById(int id);
}
