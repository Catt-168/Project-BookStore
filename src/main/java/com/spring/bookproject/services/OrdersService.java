package com.spring.bookproject.services;

import com.spring.bookproject.dto.OrdersDTO;
import com.spring.bookproject.models.Book;
import com.spring.bookproject.models.Customer;
import com.spring.bookproject.models.Orders;
import com.spring.bookproject.repositories.BookRepository;
import com.spring.bookproject.repositories.CustomerRepository;
import com.spring.bookproject.repositories.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    public OrdersService(OrdersRepository ordersRepository,
                         BookRepository bookRepository,
                         CustomerRepository customerRepository) {
        this.ordersRepository = ordersRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOrderById(Long id) {
        return ordersRepository.findById(id).orElseThrow();
    }

    public Orders createOrder(OrdersDTO ordersDTO) {
        Customer customer = customerRepository.findById(ordersDTO.getCustomerId()).orElseThrow();
        List<Book> books = bookRepository.findAllById(ordersDTO.getBook_id());
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setBooks(books);
        order.setOrderDate(new Date());
        return ordersRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        ordersRepository.deleteById(id);
    }


}
