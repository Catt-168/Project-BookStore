package com.spring.bookproject.services;

import com.spring.bookproject.dto.GenreDTO;
import com.spring.bookproject.exception.AlreadyExistException;
import com.spring.bookproject.models.Book;
import com.spring.bookproject.models.Genre;
import com.spring.bookproject.repositories.BookRepository;
import com.spring.bookproject.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreService(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre craeteGenre(GenreDTO genreDTO) {
        List<Book> books = bookRepository.findAllById(genreDTO.getBook_id());
        boolean oldGenre = genreRepository.existsByName(genreDTO.getName());
        if(oldGenre) {
            throw new AlreadyExistException("Genre with name: " + genreDTO.getName() + " already exists.");
        }
        Genre genre = new Genre();
        return saveGenre(genreDTO, genre, books);
    }

    private Genre saveGenre(GenreDTO genreDTO, Genre genre, List<Book> books) {
        genre.setName(genreDTO.getName());
        genre.setBooks(books);
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Long id, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        List<Book> books = bookRepository.findAllById(genreDTO.getBook_id());
        return saveGenre(genreDTO, genre, books);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    public void deleteAllGenres() {
        genreRepository.deleteAll();
    }
}
