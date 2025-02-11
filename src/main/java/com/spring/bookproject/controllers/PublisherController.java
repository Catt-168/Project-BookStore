package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.PublisherDTO;
import com.spring.bookproject.models.Publisher;
import com.spring.bookproject.services.PublisherService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<Publisher> getPublisher(@PathVariable Long id){
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public Publisher addPublisher(@RequestBody PublisherDTO publisherDTO) {
        return publisherService.createPublisher(publisherDTO);
    }

    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable Long id, @RequestBody PublisherDTO publisherDTO) {
        return publisherService.updatePublisher(id, publisherDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }

    @DeleteMapping
    public void deleteAllPublishers(){
        publisherService.deleteAllPublishers();
    }


}
