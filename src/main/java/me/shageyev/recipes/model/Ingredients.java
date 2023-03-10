package me.shageyev.recipes.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Ingredients {

    @NotBlank(message = "Name is necessary")
    private String productName;
    @Positive
    private int amount;
    @NotEmpty
    private String measuringUnit;

}
