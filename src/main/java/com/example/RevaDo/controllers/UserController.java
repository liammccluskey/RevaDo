package com.example.RevaDo.controllers;

import com.example.RevaDo.DTOs.UserResponseDTO;
import com.example.RevaDo.entities.User;
import com.example.RevaDo.services.UserService;
import com.example.RevaDo.utils.AuthUtil;
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
    private final AuthUtil authUtil;

    @GetMapping("/self")
    public ResponseEntity<UserResponseDTO> getSelf() {

        User user = authUtil.getCurrentUser();

        UserResponseDTO userResponse = UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return ResponseEntity.ok(userResponse);
    }
}
