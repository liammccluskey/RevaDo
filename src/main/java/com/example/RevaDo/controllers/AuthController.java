package com.example.RevaDo.controllers;

import com.example.RevaDo.DTOs.LoginRequestDTO;
import com.example.RevaDo.DTOs.RegistrationRequestDTO;
import com.example.RevaDo.DTOs.UserResponseDTO;
import com.example.RevaDo.entities.User;
import com.example.RevaDo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegistrationRequestDTO registrationRequest) {
        User user = userService.register(
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                registrationRequest.getName()
        );

        UserResponseDTO userResponse = UserResponseDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }
}
