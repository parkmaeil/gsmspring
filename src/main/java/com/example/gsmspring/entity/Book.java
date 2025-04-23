package com.example.gsmspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Lombok API
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // table 메핑 -> create table book~
// Book(Object)->Table(R) Mapping : ORM 기술
public class Book {  // 책(1) - 리뷰(N)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK : 1,2,3,4~~~

    @Column(name = "title", length = 50, nullable = false)
    private String title; // 255 , bookTitle

    private int price;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;
}
