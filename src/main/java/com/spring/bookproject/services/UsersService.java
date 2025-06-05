package com.spring.bookproject.services;

import com.spring.bookproject.dto.AuthResponseDTO;
import com.spring.bookproject.dto.AuthResult;
import com.spring.bookproject.dto.AuthorDTO;
import com.spring.bookproject.dto.UsersDTO;
import com.spring.bookproject.models.Customer;
import com.spring.bookproject.models.Users;
import com.spring.bookproject.repositories.CustomerRepository;
import com.spring.bookproject.repositories.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private static final long ACCESS_TOKEN_VALIDITY =  60 * 1000;
    private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000;

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

    public AuthResult authenticate(Users user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                String accessToken = jwtService.generateToken(user.getUsername(), ACCESS_TOKEN_VALIDITY);
                String refreshToken = jwtService.generateToken(user.getUsername(), REFRESH_TOKEN_VALIDITY);
                return new AuthResult(new AuthResponseDTO(accessToken, refreshToken));
            }
            return new AuthResult("Invalid Username or password");
        } catch(AuthenticationException e) {
            return new AuthResult("Invalid Username or password");
        }
    }
}
