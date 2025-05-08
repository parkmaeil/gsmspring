package com.example.gsmspring.repository;

import com.example.gsmspring.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // CRUD Method (X) ,,,, SQL (X) -> Hibernate 자동 생성
    // save(Book book) ---> insert into Book
    // findAll() --> select * from book(연습)
    // findById(Long id) -->select * from book where id=?
    // 1. fetch join(Book, Review)
    @Query("select distinct b from Book b LEFT JOIN FETCH b.reviews")
    public List<Book> findWithBookReviews();
    // 2. @EntityGraph
    @EntityGraph(attributePaths = {"reviews"})
    public List<Book> findAll(); // 자동생성 ?
}

/*public class EntityManager implements BookRepository{

}*/

// insert into Book(id, title, price) values( ? )
