package com.spring.bookproject.dto;

import com.spring.bookproject.enums.OrderStatus;
import lombok.Data;

@Data
public class OrdersUpdateDTO {
    private OrderStatus status;
}
