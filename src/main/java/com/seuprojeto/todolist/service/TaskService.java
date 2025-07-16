package com.seuprojeto.todolist.service;

import com.seuprojeto.todolist.dto.TaskRequestDTO;
import com.seuprojeto.todolist.dto.TaskResponseDTO;
import com.seuprojeto.todolist.exception.ResourceNotFoundException;
import com.seuprojeto.todolist.model.Task;
import com.seuprojeto.todolist.model.User;
import com.seuprojeto.todolist.repository.TaskRepository;
import com.seuprojeto.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskResponseDTO> listTasks(Long userId){
        return  taskRepository.findByUserId(userId).stream()
                .map( t -> new TaskResponseDTO(t.getId(), t.getDescription(), t.getCompleted()))
                .collect(Collectors.toList());
    }

    public TaskResponseDTO createTask(Long userId, TaskRequestDTO taskRequestDTO){
        Optional <User> user = userRepository.findById(userId);

        Task task = new Task();
        task.setDescription(taskRequestDTO.getDescription());
        task.setCompleted(taskRequestDTO.getCompleted() != null ? taskRequestDTO.getCompleted() : false);
        task.setUser(user.get());

        Task saved = taskRepository.save(task);
        return new TaskResponseDTO(saved.getId(), saved.getDescription(), saved.getCompleted());
    }

    public TaskResponseDTO updateTaskStatus(Long userId, Long taskId, Boolean completed){
        Task t = taskRepository.findById(taskId)
                .filter(task -> task.getUser().getId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        t.setCompleted(completed);
        Task updated = taskRepository.save(t);
        return  new TaskResponseDTO(updated.getId(), updated.getDescription(), updated.getCompleted());
    }

    public TaskResponseDTO updateTaskAllFields(Long userId,
                                               Long taskId,
                                               TaskRequestDTO taskRequestDTO){
        Task t = taskRepository.findById(taskId)
                .filter(task -> task.getUser().getId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        t.setDescription(taskRequestDTO.getDescription() != null ?
                taskRequestDTO.getDescription()
                : t.getDescription());

        t.setCompleted(taskRequestDTO.getCompleted() != null ?
                taskRequestDTO.getCompleted()
                : t.getCompleted()
        );

        Task updated = taskRepository.save(t);

        return new TaskResponseDTO(updated.getId(), updated.getDescription(), updated.getCompleted());
    }

    public long countCompletedTasks(Long userId){
        return taskRepository.countByUserIdAndCompletedTrue(userId);
    }

    public long countIncompletedTasks(Long userId){
        return taskRepository.countByUserIdAndCompletedFalse(userId);
    }
}
