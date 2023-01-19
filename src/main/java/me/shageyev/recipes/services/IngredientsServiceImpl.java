package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService{
    public final RecipeService recipeService;

    private static final Map<Integer, Ingredients> ingredient = new TreeMap<>();

    private static Integer ingredientId = 0;

    public IngredientsServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
@Override
    public void addIngredient(String name, int quantity, String measuringUnit) {
    Ingredients ingredients = new Ingredients(name, quantity, measuringUnit);
        recipeService.addIngredientsToRecipe(ingredients);
        ingredient.put(ingredientId++, ingredients);
        System.out.println(ingredient);
    }

//    @Override
//    public void addIngredients(String name, int quantity, String measuringUnit) {
//        Ingredients ingredients = new Ingredients(name, quantity, measuringUnit);
//        recipeService.addIngredientsToRecipe(ingredients);
//        ingredient.put(ingredientId++, ingredients);
//        System.out.println(ingredient);
//    }

    @Override
    public Ingredients getIngredients(int ingredientId) {
            return ingredient.get(ingredientId);
    }

    @Override
    public List<Ingredients> getAllIngredients() {
        List<Ingredients> ingredientsList = new ArrayList<>();
        for (Map.Entry<Integer, Ingredients> looper : ingredient.entrySet()) {
            Ingredients ingredients = looper.getValue();
            ingredientsList.add(ingredients);
        }
        return ingredientsList;
    }


}
