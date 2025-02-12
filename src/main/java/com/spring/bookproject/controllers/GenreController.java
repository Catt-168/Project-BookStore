package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.GenreDTO;
import com.spring.bookproject.exception.AlreadyExistException;
import com.spring.bookproject.models.Genre;
import com.spring.bookproject.services.GenreService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public Optional<Genre> getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id);
    }

    @PostMapping
    public ResponseEntity<?> addGenre(@RequestBody GenreDTO genreDTO) {
        try {
            return  ResponseEntity.status(200).body(genreService.craeteGenre(genreDTO));
        }catch( AlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        return genreService.updateGenre(id, genreDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }

    @DeleteMapping
    public void deleteAllGenres() {
        genreService.deleteAllGenres();
    }

}
