package com.spring.bookproject.repositories;

import com.spring.bookproject.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    public boolean existsByName(String name);
}
