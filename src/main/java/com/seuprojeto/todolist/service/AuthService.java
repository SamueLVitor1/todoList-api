package com.seuprojeto.todolist.service;

import com.seuprojeto.todolist.dto.AuthRequestDTO;
import com.seuprojeto.todolist.dto.AuthResponseDTO;
import com.seuprojeto.todolist.exception.InvalidCredentialsException;
import com.seuprojeto.todolist.model.User;
import com.seuprojeto.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponseDTO login(AuthRequestDTO loginBody){
        Optional<User> userOpt = userRepository.findByEmail(loginBody.getEmail());

        if (userOpt.isEmpty()){
            throw new InvalidCredentialsException();
        }

        User user = userOpt.get();

        if(!passwordEncoder.matches(loginBody.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());
        return new AuthResponseDTO(token);
    }


}
