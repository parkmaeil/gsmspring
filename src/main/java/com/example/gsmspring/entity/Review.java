package com.example.gsmspring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review { // 리뷰(N) - 책(1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 리뷰 1 2 3~~
    @Min(1) @Max(5)
    private int cost; // 1~5(평점)
    private LocalDateTime createdAt; // 작성일

    @ManyToOne
    @JoinColumn(name = "book_id") // FK ->Book 테이블의 PK(id)
    private Book book;
}
