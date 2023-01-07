package me.shageyev.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SecondController {

    @GetMapping("/info")
    public String studentInfo(@RequestParam String name) {
        return "Имя ученика: " + name + "\n" + "Название проекта: " + "Учусь варить борщ;" + "\n"
                + "Дата создания проекта: " + LocalDate.now() + "\n" + "Описание проекта: " +
                "Нужно узнать нужные ингридиенты, их пропорции и способы и последовательность их приготовления";
    }

}
