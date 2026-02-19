package com.example.RevaDo.controllers;

import com.example.RevaDo.DTOs.MessageResponseDTO;
import com.example.RevaDo.DTOs.TodoRequestDTO;
import com.example.RevaDo.entities.Todo;
import com.example.RevaDo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);

        return ResponseEntity.ok(todo);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Todo>> getTodos(@PathVariable Long userId) {
        return ResponseEntity.ok(
                todoService.getTodosForCurrentUser()
        );
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createTodo(@RequestBody TodoRequestDTO todoDTO) {
        todoService.createTodo(todoDTO);

        return ResponseEntity.ok(
                new MessageResponseDTO("Successfully created Todo.")
        );
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable Long todoId,
            @RequestBody TodoRequestDTO todoDTO
    ) {
        Todo todo = todoService.updateTodo(todoId, todoDTO);

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<MessageResponseDTO> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok(
                new MessageResponseDTO("Successfully deleted todo")
        );
    }
}
