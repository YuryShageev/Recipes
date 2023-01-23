package me.shageyev.recipes.model;

import me.shageyev.recipes.services.CounterService;


public class CounterModel {
    private final CounterService counterService;

    public CounterModel(CounterService counterService) {
        this.counterService = counterService;
    }
}
