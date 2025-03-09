package com.spring.bookproject.services;

import com.spring.bookproject.dto.CustomerDTO;
import com.spring.bookproject.dto.CustomerPreferncesDTO;
import com.spring.bookproject.models.Customer;
import com.spring.bookproject.models.Genre;
import com.spring.bookproject.repositories.CustomerRepository;
import com.spring.bookproject.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final GenreRepository genreRepository;

    public CustomerService(CustomerRepository customerRepository,
                           GenreRepository genreRepository) {
        this.customerRepository = customerRepository;
        this.genreRepository = genreRepository;

    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByCustomerName(username);
    }

    public Customer update(CustomerDTO customer) {
        Customer customerToUpdate = customerRepository.findByCustomerName(customer.getCustomerName());
        customerToUpdate.setCustomerName(customer.getCustomerName());
        customerToUpdate.setCustomerAddress(customer.getCustomerAddress());
        customerToUpdate.setCustomerPhone(customer.getCustomerPhone());
        return customerRepository.save(customerToUpdate);

    }

    public void addPreferredGenre(String username, CustomerPreferncesDTO customerPreferncesDTO) {
        Customer customer = findByUsername(username);
        List<Genre> genres = genreRepository.findAllById(customerPreferncesDTO.getGenre_id());
//        customer.setPreferredGenres(genres);
//        customerRepository.save(customer);
        System.out.println("Customer prefernces added");
    }

    public boolean getFistTimeLogin(String username) {
        Customer customer = findByUsername(username);
        boolean currentStatus = customer.isCustomerFistTime();
        if(currentStatus) {
            customer.setCustomerFistTime(false);
            customerRepository.save(customer);
        }
        return currentStatus;
    }


}
