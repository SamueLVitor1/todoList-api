package com.seuprojeto.todolist.service;

import com.seuprojeto.todolist.dto.TaskRequestDTO;
import com.seuprojeto.todolist.dto.TaskResponseDTO;
import com.seuprojeto.todolist.model.Task;
import com.seuprojeto.todolist.model.User;
import com.seuprojeto.todolist.repository.TaskRepository;
import com.seuprojeto.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
