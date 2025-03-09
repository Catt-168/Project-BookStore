package com.spring.bookproject.dto;

import com.spring.bookproject.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDTO {
    private Long customerId;
    private List<Long> book_id;
    private Double totalAmount;

}
