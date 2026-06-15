package com.kal1van1ch.wordrop.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_word_history")
@Data
@NoArgsConstructor
public class UserWordHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

    @Column(name = "answer_at", nullable = false)
    private LocalDateTime answerAt;
}
