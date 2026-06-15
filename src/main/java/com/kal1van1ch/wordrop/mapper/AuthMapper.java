package com.kal1van1ch.wordrop.mapper;

import com.kal1van1ch.wordrop.model.dto.UserDto;
import com.kal1van1ch.wordrop.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public UserDto toDto(User user){
        return new UserDto(
                user.getUsername(),
                user.getPassword()
        );
    }

    public User toEntity(UserDto user){
        return new User(
                null,
                user.username(),
                user.password()
        );
    }
}
