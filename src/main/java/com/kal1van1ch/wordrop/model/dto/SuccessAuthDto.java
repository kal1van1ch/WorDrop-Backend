package com.kal1van1ch.wordrop.model.dto;

import java.time.LocalDateTime;

public record SuccessAuthDto(
        String token,
        String message,
        LocalDateTime time
){}
