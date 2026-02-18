package com.example.RevaDo.utils;

import com.example.RevaDo.entities.User;
import com.example.RevaDo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final UserService userService;

    public String getCurrentUserEmail() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated.");
        }

        return authentication.getName();
    }

    public User getCurrentUser() {
        String email = getCurrentUserEmail();

        return userService.getUserByEmail(email);
    }
}
