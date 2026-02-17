package com.example.RevaDo.DTOs;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    private String name;
    private String email;
    private String password;
}
