package com.seuprojeto.todolist.controller;

import com.seuprojeto.todolist.dto.TaskRequestDTO;
import com.seuprojeto.todolist.dto.TaskResponseDTO;
import com.seuprojeto.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private Long currentUserId(){
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping
    public List<TaskResponseDTO> list() {
        return taskService.listTasks(currentUserId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(@RequestBody TaskRequestDTO dto) {
        return taskService.createTask(currentUserId(), dto);
    }
}
