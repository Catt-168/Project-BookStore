package com.spring.bookproject.services;

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

    public Users createUser(Users user) {
        boolean isUserExist = usersRepository.existsByUsername(user.getUsername());
        if(isUserExist) {
            throw new IllegalArgumentException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Customer customer = new Customer();
        customer.setCustomerName(user.getUsername());
        customerRepository.save(customer);
        return usersRepository.save(user);
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
