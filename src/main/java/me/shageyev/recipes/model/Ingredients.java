package me.shageyev.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Ingredients {

    @NotBlank(message = "Name is necessary")
    private String name;
    @Positive
    private int quantity;
    private String measuringUnit;
}
