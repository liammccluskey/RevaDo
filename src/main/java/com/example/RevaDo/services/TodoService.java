package com.example.RevaDo.services;

import com.example.RevaDo.DTOs.TodoDTO;
import com.example.RevaDo.entities.Todo;
import com.example.RevaDo.entities.User;
import com.example.RevaDo.exceptions.ApiException;
import com.example.RevaDo.repositories.TodoRepository;
import com.example.RevaDo.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthUtil authUtil;

    @Transactional
    public void toggleCompleted(Long todoId) {
        Todo todo = todoRepository
                .findByIdAndUserId(todoID, authUtil.getCurrentUser().getId())
                .orElseThrow(() -> new ApiException("Could not find a Todo with this ID", HttpStatus.BAD_REQUEST));

        todo.setCompleted(!todo.isCompleted());
    }

    public void createTodo(TodoDTO todoDTO) {
        User user = authUtil.getCurrentUser();

        Todo todo = Todo.builder()
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .user(user)
                .build();

        todoRepository.save(todo);
    }
}
