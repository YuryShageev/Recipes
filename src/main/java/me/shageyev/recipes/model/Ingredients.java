package me.shageyev.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredients {

    private String name;
    private int quantity;
    private String measuringUnit;
}
