package com.example.RevaDo.services;

import com.example.RevaDo.DTOs.SubtaskRequestDTO;
import com.example.RevaDo.entities.Subtask;
import com.example.RevaDo.entities.Todo;
import com.example.RevaDo.entities.User;
import com.example.RevaDo.exceptions.ApiException;
import com.example.RevaDo.repositories.SubtaskRepository;
import com.example.RevaDo.repositories.TodoRepository;
import com.example.RevaDo.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TodoRepository todoRepository;
    private final AuthUtil authUtil;

    // Utils

    public Todo continueIfAuthorizedToUpdateSubtask(Subtask subtask) {
        User currentUser = authUtil.getCurrentUser();

        Todo todo = subtask.getTodo();

        if (!todo.getUser().getId().equals(currentUser.getId())) {
            throw new ApiException(
                    "You are not authorized to modify this subtask.",
                    HttpStatus.UNAUTHORIZED
            );
        }

        return todo;
    }

    // Core

    @Transactional
    public Todo createSubtask(Long todoId, SubtaskRequestDTO subtaskDTO) {
        User currentUser = authUtil.getCurrentUser();

        Todo todo = todoRepository.findByIdAndUserId(todoId, currentUser.getId())
                .orElseThrow(() -> new ApiException(
                        "Could not find a todo with the given id.",
                        HttpStatus.BAD_REQUEST
                ));

        Subtask subtask = Subtask.builder()
                .description(subtaskDTO.getDescription())
                .todo(todo)
                .build();

        todo.getSubtasks().add(subtask);

        subtaskRepository.save(subtask);

        return todo;
    }

    @Transactional
    public Todo updateSubtask(Long subtaskId, SubtaskRequestDTO subtaskDTO) {
        Subtask subtask = subtaskRepository.findById(subtaskId)
                .orElseThrow(() -> new ApiException(
                        "Could not find a subtask with the given id.",
                        HttpStatus.BAD_REQUEST
                ));

        Todo todo = continueIfAuthorizedToUpdateSubtask(subtask);

        if (subtaskDTO.getDescription() != null) {
            subtask.setDescription(subtaskDTO.getDescription());
        }
        if (subtaskDTO.getCompleted() != null) {
            subtask.setCompleted(subtaskDTO.getCompleted());
        }

        return todo;
    }

    public void deleteSubtask(Long subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId)
                .orElseThrow(() -> new ApiException(
                        "Could not find a subtask with the given id.",
                        HttpStatus.BAD_REQUEST
                ));

        continueIfAuthorizedToUpdateSubtask(subtask);

        subtaskRepository.deleteById(subtaskId);
    }
}
