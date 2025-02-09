package com.spring.bookproject.services;

import com.spring.bookproject.dto.BookDTO;
import com.spring.bookproject.models.Author;
import com.spring.bookproject.models.Book;
import com.spring.bookproject.models.Genre;
import com.spring.bookproject.models.Publisher;
import com.spring.bookproject.repositories.AuthorRepository;
import com.spring.bookproject.repositories.BookRepository;
import com.spring.bookproject.repositories.GenreRepository;
import com.spring.bookproject.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       PublisherRepository publisherRepository,
                       GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> fetchBookById(Long id) {
        return bookRepository.findById(id);
    }


    public Book saveBook(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthor_id()).orElseThrow();
        Publisher publisher = publisherRepository.findById(bookDTO.getPublisher_id()).orElseThrow();
        List<Genre> genres = genreRepository.findAllById(bookDTO.getGenres_id());
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setIsbn(bookDTO.getIsbn());
        book.setLanguage(bookDTO.getLanguage());
        book.setPrice(bookDTO.getPrice());
        book.setPages(bookDTO.getPages());
        book.setRating(bookDTO.getRating());
        book.setYear(bookDTO.getYear());
        book.setImage_url(bookDTO.getImage_url());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenres(genres);
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}
