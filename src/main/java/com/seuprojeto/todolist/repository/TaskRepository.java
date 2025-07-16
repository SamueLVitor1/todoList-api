package com.seuprojeto.todolist.repository;

import com.seuprojeto.todolist.model.Task;
import com.seuprojeto.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);
}
