package com.spring.bookproject.services;

import com.spring.bookproject.dto.PublisherDTO;
import com.spring.bookproject.exception.AlreadyExistException;
import com.spring.bookproject.models.Book;
import com.spring.bookproject.models.Publisher;
import com.spring.bookproject.repositories.BookRepository;
import com.spring.bookproject.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    public PublisherService(PublisherRepository publisherRepository,
                            BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher createPublisher(PublisherDTO publisherDTO) {
        List<Book> books = bookRepository.findAllById(publisherDTO.getBook_id());
        Publisher publisher = new Publisher();
        return savePublisher(publisherDTO, publisher, books);
    }

    private Publisher savePublisher(PublisherDTO publisherDTO, Publisher publisher, List<Book> books) {
        boolean oldPublisher = publisherRepository.existsByName(publisherDTO.getName());
        if(oldPublisher) {
            throw new AlreadyExistException("Publisher with name: " + publisherDTO.getName() + " already exists.");
        }
        publisher.setName(publisherDTO.getName());
        publisher.setBooks(books);
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        List<Book> books = bookRepository.findAllById(publisherDTO.getBook_id());
        return savePublisher(publisherDTO, publisher, books);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    public void deleteAllPublishers() {
        publisherRepository.deleteAll();
    }


}
