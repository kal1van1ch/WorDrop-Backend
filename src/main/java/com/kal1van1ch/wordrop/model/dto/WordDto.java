package com.kal1van1ch.wordrop.model.dto;

import com.kal1van1ch.wordrop.model.WordLevel;

public record WordDto(
        Long id,
        String word,
        String translation,
        WordLevel level
){}
