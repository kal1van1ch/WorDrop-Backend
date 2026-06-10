package com.kal1van1ch.wordrop.model;

import java.time.LocalDateTime;

public record SuccessAuthDto(
        String message,
        LocalDateTime time
){}
