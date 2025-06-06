package com.example.gsmspring.payload;

import com.example.gsmspring.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookViewDTO {
    private Long id;
    private String title;
    private List<ReviewDTO> reviews;
}
