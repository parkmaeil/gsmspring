package com.example.gsmspring.controller;

import com.example.gsmspring.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController { // new BookController()
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
}
