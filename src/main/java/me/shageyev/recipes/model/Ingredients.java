package me.shageyev.recipes.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@EqualsAndHashCode
public class Ingredients {

    @NotBlank(message = "Name is necessary")
    private String productName;
    @Positive
    private int amount;
    @NotEmpty
    private String measuringUnit;

    public Ingredients(String productName, int amount, String measuringUnit) {
        this.productName = StringUtils.capitalize(productName);
        this.amount = amount;
        this.measuringUnit = StringUtils.trimAllWhitespace(measuringUnit);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (!productName.isEmpty() || !productName.isBlank()) {
            this.productName = productName;

        } else {
            throw new IllegalArgumentException("Название продукта обязательно");
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }
}
