package com.kal1van1ch.wordrop.service;

import com.kal1van1ch.wordrop.exception.InvalidCredentialsException;
import com.kal1van1ch.wordrop.exception.UserAlreadyExistsException;
import com.kal1van1ch.wordrop.model.User;
import com.kal1van1ch.wordrop.model.SuccessAuthDto;
import com.kal1van1ch.wordrop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository repository,
            PasswordEncoder passwordEncoder
    ){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user){
        User existingUser = repository.findByUsername(user.getUsername());
        if (existingUser != null){
            throw new UserAlreadyExistsException("Данный пользователь уже существует в системе");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User loginUser(User user){
        User existingUser = repository.findByUsername(user.getUsername());

        if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            throw new InvalidCredentialsException("Неверное имя пользователя или пароль");
        }

        return existingUser;
    }
}
