package com.spring.bookproject.controllers;

import com.spring.bookproject.dto.AuthResponseDTO;
import com.spring.bookproject.dto.AuthResult;
import com.spring.bookproject.dto.UsersDTO;
import com.spring.bookproject.models.Users;
import com.spring.bookproject.services.JWTService;
import com.spring.bookproject.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final long ACCESS_TOKEN_VALIDITY = 60 * 1000;
    private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000;

    private final UsersService usersService;
    private final JWTService jWTService;

    public AuthController(UsersService usersService, JWTService jWTService) {
        this.usersService = usersService;
        this.jWTService = jWTService;
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
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        AuthResult authResult = usersService.authenticate(user);
        if (authResult.isSuccess()) {
            return ResponseEntity.status(200).body(usersService.authenticate(user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResult.getError());
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        try {
            String username = jWTService.extractUsername(refreshToken);
            String newAccessToken = jWTService.generateToken(username, ACCESS_TOKEN_VALIDITY);
            String newRefreshToken = jWTService.generateToken(username, REFRESH_TOKEN_VALIDITY);
            return ResponseEntity.status(200).body(new AuthResult(new AuthResponseDTO(newAccessToken, newRefreshToken)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }


    }
}
