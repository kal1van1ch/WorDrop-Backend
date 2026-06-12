package com.kal1van1ch.wordrop.model;

public record StatDto(
        Long correct,
        Long wrong,
        Long totalLearned
){}
