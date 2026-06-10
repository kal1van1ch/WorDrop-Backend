package com.kal1van1ch.wordrop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "word", nullable = false, unique = true)
    private String word;

    @Column(name = "translation", nullable = false)
    private String translation;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private WordLevel level;
}
