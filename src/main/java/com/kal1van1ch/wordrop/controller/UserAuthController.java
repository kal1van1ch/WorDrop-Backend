package com.kal1van1ch.wordrop.controller;

import com.kal1van1ch.wordrop.security.JwtCore;
import com.kal1van1ch.wordrop.model.SuccessAuthDto;
import com.kal1van1ch.wordrop.model.User;
import com.kal1van1ch.wordrop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserAuthController {
    private static final Logger log = LoggerFactory.getLogger(UserAuthController.class);
    private final UserService userService;
    private final JwtCore jwtCore;

    public UserAuthController(
            UserService userService,
            JwtCore jwtCore
    ){
        this.jwtCore = jwtCore;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessAuthDto> register(@RequestBody User user){
        log.info("вызван метод register для {}", user.getUsername());

        User registeredUser = userService.registerUser(user);
        String token = jwtCore.generateToken(registeredUser.getUsername());

        String message = "Пользователь успешно зарегистрировался";

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessAuthDto(
                        token,
                        message,
                        LocalDateTime.now()
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessAuthDto> login(@RequestBody User user){
        log.info("вызван метод login для {}", user.getUsername());

        User authenedUser = userService.loginUser(user);
        String token = jwtCore.generateToken(authenedUser.getUsername());

        String message = "Пользователь успешно вошёл в систему";

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessAuthDto(
                        token,
                        message,
                        LocalDateTime.now()
                ));
    }
}
