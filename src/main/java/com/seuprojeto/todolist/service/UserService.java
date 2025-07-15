package com.seuprojeto.todolist.service;

import com.seuprojeto.todolist.dto.UserRequestDTO;
import com.seuprojeto.todolist.dto.UserResponseDTO;
import com.seuprojeto.todolist.exception.EmailAlreadyExistsException;
import com.seuprojeto.todolist.model.User;
import com.seuprojeto.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO save(UserRequestDTO userDto) {

        Optional<User> userEmailExists = userRepository.findByEmail(userDto.getEmail());

        if(userEmailExists.isPresent()){
            throw new EmailAlreadyExistsException("E-mail já cadastrado.");
        }

        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(newUser);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }
}
