package com.spring.bookproject.controllers;

import com.spring.bookproject.models.Publisher;
import com.spring.bookproject.services.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @PostMapping
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }
}
