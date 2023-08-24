package com.example.springBoot.controller;

import com.example.springBoot.entities.Book;
import com.example.springBoot.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    //atributos
    private BookRepository repository;

    //Constructor
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    //Buscar los libros
    @GetMapping("/api/books")
    public List<Book> findAll(){
        //recuperar y devolver los lobros de bd
        return repository.findAll();
    }

    //Devolver solo uno
    public Book findOneById(Long id){

    }
}
