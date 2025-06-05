package com.spring.bookproject.dto;

import lombok.Data;

@Data
public class AuthResult {
    private AuthResponseDTO  authResponseDTO;
    private String error;

    public AuthResult(AuthResponseDTO authResponseDTO) {
        this.authResponseDTO = authResponseDTO;
    }

    public AuthResult(String error) {
        this.error = error;
    }

    public boolean isSuccess(){
        return authResponseDTO != null;
    }
}
