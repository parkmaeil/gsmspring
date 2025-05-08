package com.example.gsmspring.service;

import com.example.gsmspring.entity.Book;
import com.example.gsmspring.entity.Review;
import com.example.gsmspring.payload.BookDTO;
import com.example.gsmspring.payload.BookViewDTO;
import com.example.gsmspring.payload.ReviewDTO;
import com.example.gsmspring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional
public class BookService { //new BookService()
    //@Autowired
    private final BookRepository bookRepository;

    // 책 전체 목록 가져오기 - method
    @Transactional(readOnly = true)
    public List<BookViewDTO> findAll(){
        // SQL 1번 : select * from book;
        List<Book> books=bookRepository.findAll(); // 책3권의 정보만 가져온다.
        // Book(Entity)-연관관계
        List<BookViewDTO> bookView=books.stream().map((book)->{
            // 3000권의 책에 대한 3번의 SQL이 전송
            // N             N번 : N+1문제 -> 해결? 1. FETCH JOIN, 2. @EntityGraph
            //             1번책의 리뷰가 2개->DB에서 데이터를 가지고 와야 한다.
            //                        1->select * from review where book_id=1;
            //                        2->select * from review where book_id=2;
            //                        3->select * from review where book_id=3;
            List<ReviewDTO> reviewsDTO=book.getReviews().stream().map((review)->{
               return new ReviewDTO(review.getId(), review.getCost(), review.getCreatedAt());
            }).toList();
            return new BookViewDTO(book.getId(), book.getTitle(), reviewsDTO);
        }).toList();
        return bookView; // 순환참조 문제 해결
    }
    // 책 저장하기
    @Transactional
    public Book save(BookDTO reqBook){
        Book book=new Book();
        book.setTitle(reqBook.getTitle());
        book.setPrice(reqBook.getPrice());
        return bookRepository.save(book);
        //return book;
    }
    // 특정 책 정보 가져오기(상세보기)
    @Transactional(readOnly = true)
    public Book findById(Long id){
        Optional<Book> optional=bookRepository.findById(id);
        if(!optional.isPresent()){
            throw new IllegalArgumentException("해당하는 id가 없습니다."); // 끝남
        }
        Book book=optional.get();
        return book;
    }
    // 책 수정하기
    @Transactional
    public Book save(Long id,BookDTO reqBook){
        Optional<Book> optional=bookRepository.findById(id);
        if(!optional.isPresent()){
            throw new IllegalArgumentException(" id에 해당하는 책이 없습니다.");
        }
        Book book=optional.get(); // 수정하기
        book.setTitle(reqBook.getTitle());
        book.setPrice(reqBook.getPrice());
        //return bookRepository.save(book); // update
        return book; // 더티체킹
    }

    // 특정 책을 삭제하기
    @Transactional
    public void deleteById(Long id){
       bookRepository.deleteById(id);
    }

}
