package com.spring.bookproject.controllers;

import com.spring.bookproject.models.Author;
import com.spring.bookproject.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }
}
