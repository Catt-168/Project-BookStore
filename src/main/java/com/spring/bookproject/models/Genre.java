package com.spring.bookproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "genres")
//    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Book> books = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "customer_id")  // Foreign key in Genre table
//    @JsonIgnoreProperties("preferredGenres")  // Prevents infinite recursion during JSON serialization
//    private Customer customer;

}
