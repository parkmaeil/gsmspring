package com.example.gsmspring.repository;

import com.example.gsmspring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // CRUD Method (X) ,,,, SQL (X) -> Hibernate 자동 생성
    // save(Book book) ---> insert into Book
    // findAll() --> select * from book(연습)
    // findById(Long id) -->select * from book where id=?
}

/*public class EntityManager implements BookRepository{

}*/

// insert into Book(id, title, price) values( ? )
