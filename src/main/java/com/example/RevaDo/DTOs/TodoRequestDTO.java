package com.example.RevaDo.DTOs;

import lombok.Data;

@Data
public class TodoRequestDTO {
    private String title;
    private String description;
    private Boolean completed;
}
