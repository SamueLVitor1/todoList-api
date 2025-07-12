package com.seuprojeto.todolist.service;

import com.seuprojeto.todolist.exception.EmailAlreadyExistsException;
import com.seuprojeto.todolist.model.User;
import com.seuprojeto.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {

        Optional<User> userEmailExists = userRepository.findByEmail(user.getEmail());

        if(userEmailExists.isPresent()){
            throw new EmailAlreadyExistsException("E-mail já cadastrado.");
        }

        return userRepository.save(user);
    }
}
