package com.spring.bookproject.dto;

import com.spring.bookproject.models.Genre;
import com.spring.bookproject.models.Publisher;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookDTO {
    private String title;
    private String description;
    private String isbn;
    private String language;
    private String image_url;
    private Double price;
    private Double rating;
    private int year;
    private int pages;
    private long author_id;
    private List<Long> genres_id;
    private long publisher_id;
}
