package com.example.springBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //Asociar Url a este metodo
    @GetMapping("/hola")
    public String holaMundo(){
        return "Hola Mundo desde Spring boot soy Karen Vargas";
    }
}
