package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.UsersDTO;
import com.spring.bookproject.models.Users;
import com.spring.bookproject.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsersService usersService;

    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/session")
    public String getSessionId(HttpServletRequest request) {
        return request.getRequestedSessionId();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsersDTO user) {
        try {
            return ResponseEntity.status(200).body(usersService.createUser(user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return usersService.authenticate(user);
    }
}
