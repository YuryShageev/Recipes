package me.shageyev.recipes.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Recipe {

    @NotBlank(message = "Name is necessary")
    private String name;
    @Positive
    private int cookingTime;
    @NonNull
    private String time = "минут";
    @NotEmpty
    private List<Ingredients> ingredients;
    @NotEmpty
    private List<String> steps;
}
