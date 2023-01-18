package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService{

//    private final Ingredients beetRoot = new Ingredients("Свёкла", 2, "шт");
//    private final Ingredients carrot = new Ingredients("Морковь", 2, "Шт");
//    private final Ingredients onion = new Ingredients("Лук", 1, "Шт");
//    private final Ingredients garlic = new Ingredients("Чеснок", 3, "Зубчика");
//    private final Ingredients oil = new Ingredients("Растительное масло", 2, "Ст.л.");
//    private final Ingredients potato = new Ingredients("Картофель", 4, "Шт");
//    private final Ingredients meat = new Ingredients("Мясо", 400, "г");
//    private final Ingredients water = new Ingredients("Вода", 3, "л");
//    private final Ingredients salt = new Ingredients("Соль", 1, "По вкусу");
//    private final Ingredients pepper = new Ingredients("Перец", 1, "По вкусу");
//    private final Ingredients sweetPepper = new Ingredients("Сладкий перец", 2, "Шт");
//    private final Ingredients cabbage = new Ingredients("Капуста", 300, "г");
//
//    List<Ingredients> borschIngredients = new ArrayList<>();

    private static final Map<Integer, Ingredients> ingredient = new TreeMap<>();

    private static Integer ingredientId = 0;


    @Override
    public void addIngredients(String name, int quantity, String measuringUnit) {
        Ingredients ingredients = new Ingredients(name, quantity, measuringUnit);
        ingredient.put(ingredientId++, ingredients);
        System.out.println(ingredient);
    }

    @Override
    public Ingredients getIngredients(int ingredientId) {
            return ingredient.get(ingredientId);
    }

    @Override
    public List getAllIngredients() {
        List<Ingredients> ingredientsList = new ArrayList<>();
        for (Map.Entry<Integer, Ingredients> looper : ingredient.entrySet()) {
            Ingredients ingredients = looper.getValue();
            ingredientsList.add(ingredients);
        }
        return ingredientsList;
    }


}
