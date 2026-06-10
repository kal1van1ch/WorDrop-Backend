package com.kal1van1ch.wordrop.exception;

import com.kal1van1ch.wordrop.model.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handlerUserAlreadyExistsException(UserAlreadyExistsException e){
        log.info("Called UserAlreadyExistsException", e);

    var errorDto = new ErrorDto(
            "Такой пользователь уже есть",
            e.getMessage(),
            LocalDateTime.now()
    );

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(errorDto);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDto> handlerInvalidCredentialsException(InvalidCredentialsException e){
        log.info("Called InvalidCredentialsException", e);

        var errorDto = new ErrorDto(
                "Пользователь неавторизован",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorDto);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> handlerAuthenticationException(AuthenticationException e){
        log.error("Called InvalidCredentialsException", e);

        var errorDto = new ErrorDto(
                "Ошибка авторизации",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDto);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> handlerNoHandlerFoundException(NoHandlerFoundException e){
        log.error("called NoHandlerFoundException", e);

        var errorDto = new ErrorDto(
                "Несуществующий маршрут",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handlerException(Exception e){
        log.error("Called Exception", e);

        var errorDto = new ErrorDto(
                "Ошибка на сервере",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorDto);
    }
}
