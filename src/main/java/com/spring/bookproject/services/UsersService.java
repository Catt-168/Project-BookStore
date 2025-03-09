package com.spring.bookproject.services;

import com.spring.bookproject.dto.UsersDTO;
import com.spring.bookproject.models.Customer;
import com.spring.bookproject.models.Users;
import com.spring.bookproject.repositories.CustomerRepository;
import com.spring.bookproject.repositories.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final CustomerRepository customerRepository;

    public UsersService(UsersRepository usersRepository,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager,
                        JWTService jwtService,
                        CustomerRepository customerRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
    }

    public Users createUser(UsersDTO user) {
        boolean isUserExist = usersRepository.existsByUsername(user.getUsername());
        if(isUserExist) {
            throw new IllegalArgumentException("User already exists");
        }
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Customer customer = new Customer();
        customer.setCustomerName(user.getUsername());
        customer.setCustomerPhone(user.getPhone());
        customer.setCustomerAddress(user.getAddress());
        customerRepository.save(customer);
        return usersRepository.save(newUser);
    }

    public String authenticate(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "Invalid username or password";
    }
}
