package com.example.springBoot.controller;

import com.example.springBoot.entities.Book;
import com.example.springBoot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    //Logs
    private final Logger log = LoggerFactory.getLogger(BookController.class);

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
    public ResponseEntity<Book> crear(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));

        //Validacion si ya hay un id creado
        if (book.getId() !=null){
            log.warn("Trying to create a book with id");
            System.out.println("Desde el sistem");
            return ResponseEntity.badRequest().build();
        }
        Book result = repository.save(book);
        return ResponseEntity.ok(result);
    }

    //Actualizar libro
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody  Book book){
        //Que ingresen un id
        if (book.getId() == null){
            log.warn("Trying to update a non existen book");
            return ResponseEntity.badRequest().build();
        }

        //Si el libro existe
        if (!repository.existsById(book.getId())){
            log.warn("The book was not found");
            return ResponseEntity.notFound().build();
        }

        Book result = repository.save(book);
        return ResponseEntity.ok(result);
    }

    //Delete
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if (!repository.existsById(id)){
            log.warn("The book was not found");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Delete all
    @DeleteMapping("/api/books")
    public  ResponseEntity<Book> deleteAll(){
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
