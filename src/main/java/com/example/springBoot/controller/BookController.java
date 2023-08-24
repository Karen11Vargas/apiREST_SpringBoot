package com.example.springBoot.controller;

import com.example.springBoot.entities.Book;
import com.example.springBoot.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable  Long id){
        Optional<Book> book = repository.findById(id);

        //Opcion 1
        //Se devuelve un ok con la data tipo json
        if (book.isPresent())
            return ResponseEntity.ok(book.get());
        else
            //Construye una respuesta de no found
            return ResponseEntity.notFound().build();

        //opcion 2, reducida
        //return book.orElse(null);
    }


    //Guardar libro
    @PostMapping("/api/books")
    public Book crear(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        return repository.save(book);
    }
}
