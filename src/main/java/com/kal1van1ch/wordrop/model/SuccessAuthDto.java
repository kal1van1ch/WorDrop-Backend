package com.kal1van1ch.wordrop.model;

import java.time.LocalDateTime;

public record SuccessAuthDto(
        String token,
        String message,
        LocalDateTime time
){}
