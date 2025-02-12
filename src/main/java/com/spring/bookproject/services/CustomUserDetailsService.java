package com.spring.bookproject.services;

import com.spring.bookproject.models.UserPrincipal;
import com.spring.bookproject.models.Users;
import com.spring.bookproject.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}
