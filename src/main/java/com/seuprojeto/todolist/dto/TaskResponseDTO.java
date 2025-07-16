package com.seuprojeto.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String description;
    private Boolean completed;
}

