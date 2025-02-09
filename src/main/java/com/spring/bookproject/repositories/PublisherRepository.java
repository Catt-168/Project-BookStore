package com.spring.bookproject.repositories;

import com.spring.bookproject.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
