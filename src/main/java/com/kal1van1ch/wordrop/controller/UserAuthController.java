package com.kal1van1ch.wordrop.controller;

import com.kal1van1ch.wordrop.model.SuccessAuthDto;
import com.kal1van1ch.wordrop.model.User;
import com.kal1van1ch.wordrop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private static final Logger log = LoggerFactory.getLogger(UserAuthController.class);
    private final UserService userService;

    public UserAuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessAuthDto> register(@RequestBody User user){
        log.info("вызван метод register");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessAuthDto> login(@RequestBody User user){
        log.info("вызван метод login");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.loginUser(user));
    }
}
