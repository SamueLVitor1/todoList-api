package com.seuprojeto.todolist.controller;

import com.seuprojeto.todolist.dto.AuthRequestDTO;
import com.seuprojeto.todolist.dto.AuthResponseDTO;
import com.seuprojeto.todolist.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO){
        AuthResponseDTO response = authService.login(authRequestDTO);
        return ResponseEntity.ok(response);
    }

}
