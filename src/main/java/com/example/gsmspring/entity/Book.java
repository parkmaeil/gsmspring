package com.example.gsmspring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Lombok API
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id; //null
    private String title;
    private int price;
}
