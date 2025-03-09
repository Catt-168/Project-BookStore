package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.OrdersDTO;
import com.spring.bookproject.dto.OrdersUpdateDTO;
import com.spring.bookproject.models.Orders;
import com.spring.bookproject.services.OrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id);
    }

    @GetMapping("/byCustomer")
    public List<Orders> getOrderByCustomerId(@RequestParam Long customerId) {
        return ordersService.findOrdersByCustomerId(customerId);
    }

    @PostMapping
    public Orders createOrder(@RequestBody OrdersDTO ordersDTO) {
        return ordersService.createOrder(ordersDTO);
    }

    @PutMapping("/{id}")
    public Orders updateOrder(@PathVariable Long id, @RequestBody OrdersUpdateDTO ordersUpdateDTO) {
        return ordersService.updateOrder(id, ordersUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        ordersService.deleteOrderById(id);
    }

}
