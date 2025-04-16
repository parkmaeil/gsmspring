package com.example.gsmspring.controller;

import com.example.gsmspring.entity.Book;
import com.example.gsmspring.payload.BookDTO;
import com.example.gsmspring.payload.BookViewDTO;
import com.example.gsmspring.repository.BookRepository;
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
    private BookRepository bookRepository; // DI(의존성주입)
    // HandlerMapping
    @GetMapping("/")
    public String home(){
        return "Hello World";
    }
    // GET : http://localhost:8081/api/books
    @GetMapping("/books")
    public List<BookViewDTO> getAllList(){
        List<Book> books=bookRepository.findAll();
        // Book->BookViewDTO : map()
        List<BookViewDTO> bookView=books.stream().map((book)->{
          return new BookViewDTO(book.getId(), book.getTitle());
        }).toList();
        // 순환참조문제 발생 -> 해결(DTO)
        return bookView; // -->HttpMessageConverter-> [ {    },{    }, {     } ]
    }
    // POST : http://localhost:8081/api/books
    @PostMapping("/books")
    public Book bookSave(@RequestBody BookDTO reqBook){  // JSON : { "title":"자바", "price":35000 }
        // BookDTO -> Book
        Book book=new Book();
        book.setTitle(reqBook.getTitle());
        book.setPrice(reqBook.getPrice());
        return bookRepository.save(book);
    }

    // GET : http://localhost:8081/api/books/3
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
       Optional<Book> optional=bookRepository.findById(id);
       if(!optional.isPresent()){
           throw new RuntimeException("데이터가 없습니다.");
       }
       Book book=optional.get();
       //BookViewDTO
       return new ResponseEntity<>(book, HttpStatus.OK); // JSON ?
    }
}
