package com.kal1van1ch.wordrop.service;

import com.kal1van1ch.wordrop.exception.InvalidCredentialsException;
import com.kal1van1ch.wordrop.exception.UserAlreadyExistsException;
import com.kal1van1ch.wordrop.mapper.AuthMapper;
import com.kal1van1ch.wordrop.model.dto.UserDto;
import com.kal1van1ch.wordrop.model.entity.User;
import com.kal1van1ch.wordrop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper mapper;

    public UserService(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            AuthMapper mapper
    ){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Transactional
    public User registerUser(UserDto userDto){
        User user = mapper.toEntity(userDto);
        User existingUser = repository.findByUsername(user.getUsername());
        if (existingUser != null){
            throw new UserAlreadyExistsException("Данный пользователь уже существует в системе");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User loginUser(UserDto userDto){
        User user = mapper.toEntity(userDto);
        User existingUser = repository.findByUsername(user.getUsername());

        if (existingUser == null || !passwordEncoder.matches(userDto.password(), existingUser.getPassword())){
            throw new InvalidCredentialsException("Неверное имя пользователя или пароль");
        }

        return existingUser;
    }
}
