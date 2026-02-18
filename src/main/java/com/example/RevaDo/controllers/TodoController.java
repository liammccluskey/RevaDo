package com.example.RevaDo.controllers;

import com.example.RevaDo.DTOs.MessageResponseDTO;
import com.example.RevaDo.DTOs.TodoDTO;
import com.example.RevaDo.entities.Todo;
import com.example.RevaDo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/")
    public ResponseEntity<MessageResponseDTO> createTodo(@RequestBody TodoDTO todoDTO) {
        todoService.createTodo(todoDTO);

        return ResponseEntity.ok(
                new MessageResponseDTO("Successfully created Todo.")
        );
    }

//    @PatchMapping("/{todoId}")
//    public ResponseEntity<MessageResponseDTO> updateTodo(
//            @PathVariable Long todoId,
//            @RequestBody TodoDTO todoDTO
//    ) {
//
//    }

    @PatchMapping("/{todoId}/toggle-completed")
    public ResponseEntity<MessageResponseDTO> toggleCompleted(@PathVariable Long todoId) {
        todoService.toggleCompleted(todoId);

        return ResponseEntity.ok(
                new MessageResponseDTO("Successfully updated todo.")
        );
    }
}
