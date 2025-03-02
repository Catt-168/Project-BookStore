package com.spring.bookproject.services;

import com.spring.bookproject.dto.BookDTO;
import com.spring.bookproject.exception.AlreadyExistException;
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

import java.rmi.AlreadyBoundException;
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

    public Book createBook(BookDTO bookDTO) throws AlreadyExistException {
        Author author = authorRepository.findById(bookDTO.getAuthor_id()).orElseThrow();
        Publisher publisher = publisherRepository.findById(bookDTO.getPublisher_id()).orElseThrow();
        List<Genre> genres = genreRepository.findAllById(bookDTO.getGenres_id());
        Boolean oldBook = bookRepository.existsByTitle(bookDTO.getTitle());
        if(oldBook) {
            throw new AlreadyExistException("A Book with title: " + bookDTO.getTitle() + " already exists.");
        }
        Book book = new Book();
        saveBook(bookDTO, book, author, publisher, genres);
        return book;
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow();
        Author author = authorRepository.findById(bookDTO.getAuthor_id()).orElseThrow();
        Publisher publisher = publisherRepository.findById(bookDTO.getPublisher_id()).orElseThrow();
        List<Genre> genres = genreRepository.findAllById(bookDTO.getGenres_id());
        saveBook(bookDTO, book, author, publisher, genres);
        return book;
    }

    private void saveBook(BookDTO bookDTO, Book book, Author author, Publisher publisher, List<Genre> genres) {
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
        bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    public List<Book> fetchByGenre(Long genreId) {
        return bookRepository.fetchBookByGenreId(genreId);
    }
}
