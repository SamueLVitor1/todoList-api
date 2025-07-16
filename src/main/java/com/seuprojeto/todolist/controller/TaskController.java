package com.seuprojeto.todolist.controller;

import com.seuprojeto.todolist.dto.TaskRequestDTO;
import com.seuprojeto.todolist.dto.TaskResponseDTO;
import com.seuprojeto.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateStatus(@PathVariable Long id,
                                                       @RequestBody Map<String, Boolean> body) {
        Boolean completed = body.get("completed");

        return ResponseEntity
                .status(204)
                .body(taskService.updateTaskStatus(
                        currentUserId(), id,
                        completed)
                );
    }

    @PutMapping("/{id}")
    public  TaskResponseDTO updtateAll(@PathVariable Long id,
                                       @RequestBody TaskRequestDTO taskRequestDTO){
        return  taskService.updateTaskAllFields(currentUserId(), id, taskRequestDTO);
    }

}
