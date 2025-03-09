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
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

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
        if (oldBook) {
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

    public Set<Book> editorPick(List<Long> genreIds) {
        // Validate input: Ensure at least 3 genre IDs are provided
        if (genreIds == null || genreIds.size() < 3) {
            throw new IllegalArgumentException("At least 3 genre IDs are required.");
        }

        // Fetch books for each genre
        List<Book> book1 = bookRepository.fetchBookByGenreId(genreIds.get(0));
        List<Book> book2 = bookRepository.fetchBookByGenreId(genreIds.get(1));
        List<Book> book3 = bookRepository.fetchBookByGenreId(genreIds.get(2));

        // If all book lists are empty, return an empty set
        if (book1.isEmpty() && book2.isEmpty() && book3.isEmpty()) {
            return Collections.emptySet();
        }

        // Combine all book lists into one list of lists
        List<List<Book>> allLists = Arrays.asList(book1, book2, book3);

        // Use a HashSet to store selected books (ensures uniqueness)
        Set<Book> allSelectedBooks = new HashSet<>();
        Random random = new Random();

        // Step 1: Pick at least one book from each non-empty list
        for (List<Book> books : allLists) {
            if (!books.isEmpty()) {
                allSelectedBooks.add(books.get(random.nextInt(books.size())));
            }
        }

        // Step 2: Continue picking random books until we have 4 or no more books are available
        while (allSelectedBooks.size() < 5) {
            boolean added = false;
            for (List<Book> books : allLists) {
                if (!books.isEmpty()) {
                    Book randomBook = books.get(random.nextInt(books.size()));
                    if (allSelectedBooks.add(randomBook)) { // Add only if not already in the set
                        added = true;
                    }
                }
            }
            // If no new book was added in this iteration, break to avoid infinite loop
            if (!added) {
                break;
            }
        }

        // Return the selected books (even if fewer than 4)
        return allSelectedBooks;
    }




}
