package com.seuprojeto.todolist.dto;

import lombok.Data;

@Data
public class TaskRequestDTO {

    private String description;
    private Boolean completed;

}
