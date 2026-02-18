package com.example.RevaDo.controllers;

import com.example.RevaDo.DTOs.UserResponseDTO;
import com.example.RevaDo.entities.User;
import com.example.RevaDo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/self")
    public ResponseEntity<UserResponseDTO> getSelf(Principal principal) {
        String email = principal.getName();

        User user = userService.getUserByEmail(email);

        UserResponseDTO userResponse = UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return ResponseEntity.ok(userResponse);
    }
}
