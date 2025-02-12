package com.spring.bookproject.services;

import com.spring.bookproject.models.Users;
import com.spring.bookproject.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository,PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users createUser(Users user) {
        boolean isUserExist = usersRepository.existsByUsername(user.getUsername());
        if(isUserExist) {
            throw new IllegalArgumentException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }
}
