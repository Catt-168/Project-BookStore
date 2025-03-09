package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.CustomerDTO;
import com.spring.bookproject.dto.CustomerFavDTO;
import com.spring.bookproject.dto.CustomerPreferncesDTO;
import com.spring.bookproject.models.Customer;
import com.spring.bookproject.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/first")
    public boolean getFirstTimeLogin(@RequestParam String username) {
        System.out.println("First time login" + username);
        return true;
//        return customerService.getFistTimeLogin(username);
    }

    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam String username) {
        return customerService.findByUsername(username);
    }

    @PostMapping("/pref")
    public void postCustomerPreferences(@RequestParam String username, @RequestBody CustomerPreferncesDTO customerPreferncesDTO){
        System.out.println("CUSTOMER PREF START" + username + customerPreferncesDTO);
        customerService.addPreferredGenre(username,customerPreferncesDTO);
    }

    @PostMapping("/fav")
    public void postCustomerFavorites(@RequestParam String username, @RequestBody CustomerFavDTO favorites){
        String fav = favorites.getFavorites();
        customerService.addPrefences(username, fav);
    }


    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody CustomerDTO customer) {
        return customerService.update(customer);
    }

}
