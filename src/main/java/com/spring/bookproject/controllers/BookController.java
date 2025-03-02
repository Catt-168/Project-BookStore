package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.BookDTO;
import com.spring.bookproject.exception.AlreadyExistException;
import com.spring.bookproject.models.Book;
import com.spring.bookproject.models.Orders;
import com.spring.bookproject.services.BookService;
import com.spring.bookproject.services.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

   private final BookService bookService;
   private final OrdersService ordersService;

    public BookController(BookService bookService,
                          OrdersService ordersService) {
        this.bookService = bookService;
        this.ordersService = ordersService;
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
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        try {
            return ResponseEntity.status(200).body(bookService.createBook(bookDTO));
        } catch (AlreadyExistException e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
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
