package com.spring.bookproject.services;

import com.spring.bookproject.models.Genre;
import com.spring.bookproject.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {return genreRepository.findAll();}

    public Genre craeteGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
