package com.example.RevaDo.services;

import com.example.RevaDo.entities.User;
import com.example.RevaDo.repositories.UserRepository;
import com.example.RevaDo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Could not find a user with this email."));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Password is invalid.");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    public User register(String email, String rawPassword, String name) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("There is already a user with this email.");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .name(name)
                .build();

        return userRepository.save(user);
    }
}
