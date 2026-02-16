package com.example.RevaDo.controllers;

import com.example.RevaDo.services.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subtasks")
@RequiredArgsConstructor
public class SubtaskController {

    private final SubtaskService subtaskService;
}
