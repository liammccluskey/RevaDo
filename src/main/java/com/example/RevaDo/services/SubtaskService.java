package com.example.RevaDo.services;

import com.example.RevaDo.repositories.SubtaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubtaskService {

    private final SubtaskRepository subtaskRepository;
}
