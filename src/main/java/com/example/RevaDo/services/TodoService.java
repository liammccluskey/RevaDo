package com.example.RevaDo.services;

import com.example.RevaDo.DTOs.TodoRequestDTO;
import com.example.RevaDo.entities.Todo;
import com.example.RevaDo.entities.User;
import com.example.RevaDo.exceptions.ApiException;
import com.example.RevaDo.repositories.TodoRepository;
import com.example.RevaDo.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthUtil authUtil;

    public Todo getTodo(Long todoId) {
        User currentUser = authUtil.getCurrentUser();

        return todoRepository.findByIdAndUserId(todoId, currentUser.getId())
                .orElseThrow(() -> new ApiException(
                        "Could not find a todo with that id.",
                        HttpStatus.BAD_REQUEST
                ));
    }

    public List<Todo> getTodosForCurrentUser() {
        User currentUser = authUtil.getCurrentUser();

        return todoRepository.findByUser_IdOrderByCreatedAtDesc(currentUser.getId());
    }

    public void createTodo(TodoRequestDTO todoDTO) {
        User currentUser = authUtil.getCurrentUser();

        Todo todo = Todo.builder()
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .user(currentUser)
                .build();

        todoRepository.save(todo);
    }

    @Transactional
    public Todo updateTodo(Long todoId, TodoRequestDTO todoDTO) {
        User currentUser = authUtil.getCurrentUser();

        Todo todo = todoRepository.findByIdAndUserId(todoId, currentUser.getId())
                .orElseThrow(() -> new ApiException(
                        "Could not find a todo with the given Id.",
                        HttpStatus.BAD_REQUEST
                ));

        if (todoDTO.getTitle() != null) {
            todo.setTitle(todoDTO.getTitle());
        }
        if (todoDTO.getDescription() != null) {
            todo.setDescription(todoDTO.getDescription());
        }
        if (todoDTO.getCompleted() != null) {
            todo.setCompleted(todoDTO.getCompleted());
        }

        return todo;
    }

    public void deleteTodo(Long todoId) {
        User currentUser = authUtil.getCurrentUser();

        Todo todo = todoRepository.findByIdAndUserId(todoId, currentUser.getId())
                .orElseThrow(() -> new ApiException("Could not find a todo with the given Id.", HttpStatus.BAD_REQUEST));

        todoRepository.deleteById(todoId);
    }
}
