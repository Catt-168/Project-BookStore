package com.spring.bookproject.services;

import com.spring.bookproject.dto.AuthorDTO;
import com.spring.bookproject.models.Author;
import com.spring.bookproject.models.Book;
import com.spring.bookproject.repositories.AuthorRepository;
import com.spring.bookproject.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(AuthorDTO authorDTO) {
        List<Book> books = bookRepository.findAllById(authorDTO.getBook_id());
        Author author = new Author();
        return saveAuthor(authorDTO, author, books);
    }

    private Author saveAuthor(AuthorDTO authorDTO, Author author, List<Book> books) {
        author.setName(authorDTO.getName());
        author.setBooks(books);
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).orElseThrow();
        List<Book> books = bookRepository.findAllById(authorDTO.getBook_id());
        return saveAuthor(authorDTO, author, books);
    }

    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }

    public void deleteAuthorById(long id) {
        authorRepository.deleteById(id);
    }


}
