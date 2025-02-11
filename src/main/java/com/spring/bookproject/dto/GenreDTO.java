package com.spring.bookproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {
    private String name;
    private List<Long> book_id;
}
