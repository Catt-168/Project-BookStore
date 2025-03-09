package com.spring.bookproject.dto;

import lombok.Data;

import java.util.List;


@Data
public class CustomerPreferncesDTO {
    private List<Long> genre_id;
}
