package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService{

    private final Ingredients beetRoot = new Ingredients("Свёкла", 2, "шт");
    private final Ingredients carrot = new Ingredients("Морковь", 2, "Шт");
    private final Ingredients onion = new Ingredients("Лук", 1, "Шт");
    private final Ingredients garlic = new Ingredients("Чеснок", 3, "Зубчика");
    private final Ingredients oil = new Ingredients("Растительное масло", 2, "Ст.л.");
    private final Ingredients potato = new Ingredients("Картофель", 4, "Шт");
    private final Ingredients meat = new Ingredients("Мясо", 400, "г");
    private final Ingredients water = new Ingredients("Вода", 3, "л");
    private final Ingredients salt = new Ingredients("Соль", 1, "По вкусу");
    private final Ingredients pepper = new Ingredients("Перец", 1, "По вкусу");
    private final Ingredients sweetPepper = new Ingredients("Сладкий перец", 2, "Шт");
    private final Ingredients cabbage = new Ingredients("Капуста", 300, "г");

    List<Ingredients> borschIngredients = new ArrayList<>();

    private static final Map<Long, Ingredients> ingredient = new TreeMap<>();

    private static long ingredientId = 0;


    @Override
    public void addIngredients(String name) {
        String name1 = "beetroot";
        String name2 = "carrot";
        String name3 = "onion";
        String name4 = "garlic";
        String name5 = "oil";
        String name6 = "potato";
        String name7 = "meat";
        String name8 = "water";
        String name9 = "salt";
        String name10 = "pepper";
        String name11 = "sweetpepper";
        String name12 = "cabbage";

        if (name.equals(name1) && !name.isBlank()) {
            ingredient.put(ingredientId++, beetRoot);
            System.out.println("Вы добавили свёклу");
        }
        if (name.equals(name2) && !name.isBlank()) {
            ingredient.put(ingredientId++, carrot);
            System.out.println("Вы добавили морковь");
        }
        if (name.equals(name3) && !name.isBlank()) {
            ingredient.put(ingredientId++, onion);
            System.out.println("Вы добавили лук");
        }
        if (name.equals(name4) && !name.isBlank()) {
            ingredient.put(ingredientId++, garlic);
            System.out.println("Вы добавили чеснок");
        }
        if (name.equals(name5) && !name.isBlank()) {
            ingredient.put(ingredientId++, oil);
            System.out.println("Вы добавили растительное масло");
        }
        if (name.equals(name6) && !name.isBlank()) {
            ingredient.put(ingredientId++, potato);
            System.out.println("Вы добавили картофель");
        }
        if (name.equals(name7) && !name.isBlank()) {
            ingredient.put(ingredientId++, meat);
            System.out.println("Вы добавили мясо");
        }
        if (name.equals(name8) && !name.isBlank()) {
            ingredient.put(ingredientId++, water);
            System.out.println("Вы добавили воду");
        }
        if (name.equals(name9) && !name.isBlank()) {
            ingredient.put(ingredientId++, salt);
            System.out.println("Вы добавили соль");
        }
        if (name.equals(name10) && !name.isBlank()) {
            ingredient.put(ingredientId++, pepper);
            System.out.println("Вы добавили перец");
        }
        if (name.equals(name11) && !name.isBlank()) {
            ingredient.put(ingredientId++, sweetPepper);
            System.out.println("Вы добавили сладкий перец");
        }
        if (name.equals(name12) && !name.isBlank()) {
            ingredient.put(ingredientId++, cabbage);
            System.out.println("Вы добавили капусту");
        }
    }

    @Override
    public void getIngredients(long ingredientId) {
        System.out.println(ingredient.get(ingredientId));
    }


}
