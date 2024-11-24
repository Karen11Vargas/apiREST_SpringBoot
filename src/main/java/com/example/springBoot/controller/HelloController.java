package com.example.springBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${app.message}")
    String message;

    //Asociar Url a este metodo
    @GetMapping("/hola")
    public String holaMundo(){
        System.out.println(message);
        return "Hola Mundo desde Spring boot soy Karen Vargas";
    }
}
