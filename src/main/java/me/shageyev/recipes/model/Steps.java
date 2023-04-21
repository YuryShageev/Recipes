package me.shageyev.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Steps {

    @NotBlank(message = "Instruction is necessary!")
    private String steps;

    @Override
    public String toString() {
        return getSteps() + "\n" + " ";
    }
}
