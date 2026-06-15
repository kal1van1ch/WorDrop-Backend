package com.kal1van1ch.wordrop.model.dto;

import java.time.LocalDateTime;

public record ErrorDto(
        String error,
        String message,
        LocalDateTime timestamp
){}
