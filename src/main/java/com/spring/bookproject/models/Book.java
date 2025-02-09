package com.spring.bookproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String isbn;
    private String language;
    private String image_url;
    private Double price;
    private Double rating;
    private int year;
    private int pages;

    @ManyToOne(fetch = FetchType.EAGER)  // Ensures author is always loaded
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("books")
    private Author author;

    @ManyToMany
    @JoinTable(name = "genre_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonIgnoreProperties("books")
    private List<Genre> genres = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("books")
    private Publisher publisher;

}
