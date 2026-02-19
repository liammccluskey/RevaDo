package com.example.RevaDo.controllers;

import com.example.RevaDo.DTOs.MessageResponseDTO;
import com.example.RevaDo.DTOs.SubtaskRequestDTO;
import com.example.RevaDo.entities.Todo;
import com.example.RevaDo.services.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtasks")
@RequiredArgsConstructor
public class SubtaskController {

    private final SubtaskService subtaskService;

    @PostMapping("/{todoId}")
    public ResponseEntity<Todo> createSubtask(
            @PathVariable Long todoId,
            @RequestBody SubtaskRequestDTO subtaskDTO
    ) {
        Todo todo = subtaskService.createSubtask(todoId, subtaskDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todo);
    }

    @PatchMapping("/{subtaskId}")
    public ResponseEntity<Todo> updateSubtask(
            @PathVariable Long subtaskId,
            @RequestBody SubtaskRequestDTO subtaskDTO
    ) {
        Todo todo = subtaskService.updateSubtask(subtaskId, subtaskDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todo);
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<MessageResponseDTO> deleteSubtask(@PathVariable Long subtaskId) {
        subtaskService.deleteSubtask(subtaskId);

        return ResponseEntity.ok(
                new MessageResponseDTO("Successfully deleted subtask.")
        );
    }
}
