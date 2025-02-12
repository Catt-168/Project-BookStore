package com.spring.bookproject.repositories;

import com.spring.bookproject.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
