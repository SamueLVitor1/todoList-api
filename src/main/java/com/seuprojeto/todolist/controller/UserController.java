package com.seuprojeto.todolist.controller;

import com.seuprojeto.todolist.dto.UserRequestDTO;
import com.seuprojeto.todolist.dto.UserResponseDTO;
import com.seuprojeto.todolist.model.User;
import com.seuprojeto.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user){
     UserResponseDTO userSaved = userService.save(user);
     return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }


}
