package com.example.gsmspring.controller;

import com.example.gsmspring.entity.Book;
import com.example.gsmspring.payload.BookDTO;
import com.example.gsmspring.payload.BookViewDTO;
import com.example.gsmspring.repository.BookRepository;
import com.example.gsmspring.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Tag(name = "Book Controller", description = "Book management")
public class BookController { // new BookController()

    @Autowired
    private BookService bookService;
    // HandlerMapping
    @GetMapping("/")
    public String home(){
        return "Hello World";
    }
    // GET : http://localhost:8081/api/books
    @GetMapping("/books")
    public List<BookViewDTO> getAllList(){
        List<BookViewDTO> books=bookService.findAll();
        // Book->BookViewDTO : map()
        // 순환참조문제 발생 -> 해결(DTO)
        return books; // -->HttpMessageConverter-> [ {    },{    }, {     } ]
    }
    // POST : http://localhost:8081/api/books
    @PostMapping("/books")
    public Book bookSave(@RequestBody BookDTO reqBook){  // JSON : { "title":"자바", "price":35000 }
        // BookDTO -> Book
        return bookService.save(reqBook);
    }

    // GET : http://localhost:8081/api/books/3
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Book book=bookService.findById(id);
       //BookViewDTO
       return new ResponseEntity<>(book, HttpStatus.OK); // JSON ?
    }

    @PutMapping("/books/{id}") // == @PostMapping("/books")
    public Book save(@PathVariable Long id, @RequestBody BookDTO reqBook){
         return bookService.save(id, reqBook);
    }

    @DeleteMapping("/books/{id}")
    public void bookDelete(@PathVariable Long id){
        bookService.deleteById(id);
    }
}
