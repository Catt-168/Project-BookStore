package com.spring.bookproject.services;

import com.spring.bookproject.models.Publisher;
import com.spring.bookproject.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher savePublisher(Publisher publisher) {
        System.out.println(publisher);
        return publisherRepository.save(publisher);
    }
}
