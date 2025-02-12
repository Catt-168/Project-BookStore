package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.PublisherDTO;
import com.spring.bookproject.exception.AlreadyExistException;
import com.spring.bookproject.models.Publisher;
import com.spring.bookproject.services.PublisherService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addPublisher(@RequestBody PublisherDTO publisherDTO) {
        try {
            return  ResponseEntity.status(200).body(publisherService.createPublisher(publisherDTO));
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
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
