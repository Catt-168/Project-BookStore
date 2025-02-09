package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.BookDTO;
import com.spring.bookproject.models.Book;
import com.spring.bookproject.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

   private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.fetchBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
         bookService.deleteBookById(id);
    }

    @DeleteMapping
    public void deleteAllBooks() {
        bookService.deleteAllBooks();
    }
}
