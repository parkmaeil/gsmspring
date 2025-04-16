package com.example.gsmspring.controller;

import com.example.gsmspring.entity.Book;
import com.example.gsmspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController { // new BookController()

    @Autowired
    private BookRepository bookRepository; // DI(의존성주입)
    // HandlerMapping
    @GetMapping("/")
    public String home(){
        return "Hello World";
    }

    @GetMapping("/books")
    public List<Book> getAllList(){
        Book book1=new Book(1L, "자바", 30000);
        Book book2=new Book(2L, "스프링", 32000);
        Book book3=new Book(3L, "오라클", 35000);
        List<Book> list=new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        return list; // -->HttpMessageConverter-> [ {    },{    }, {     } ]
    }

    @PostMapping("/books")
    public Book bookSave(@RequestBody Book book){  // JSON : { "title":"자바", "price":35000 }
        return bookRepository.save(book);
    }
}
