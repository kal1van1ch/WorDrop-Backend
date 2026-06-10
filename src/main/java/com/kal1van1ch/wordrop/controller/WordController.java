package com.kal1van1ch.wordrop.controller;

import com.kal1van1ch.wordrop.model.SuccessAuthDto;
import com.kal1van1ch.wordrop.model.Word;
import com.kal1van1ch.wordrop.model.WordLevel;
import com.kal1van1ch.wordrop.model.WordTransferDto;
import com.kal1van1ch.wordrop.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/words")
public class WordController {
    private static final Logger log = LoggerFactory.getLogger(WordController.class);
    private final WordService service;

    public WordController(WordService service){
        this.service = service;
    }

    @GetMapping("/random")
    public ResponseEntity<Word> getRandomWord(@RequestParam WordLevel level){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Пользователю {} выдана карточка уровня {}", username, level);

        Word word = service.getRandomWord(level, username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(word);
    }

    @PostMapping("/answer")
    public ResponseEntity<SuccessAuthDto> userAnswer(@RequestBody WordTransferDto answer){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info("Пользователь {} ввёл ответ - результат: {}", username, answer.isCorrect());

        service.logAnswerResult(answer.isCorrect(), answer.wordId(), username);

        String message = String.format("Пользователь дал ответ, результат - %b", answer.isCorrect());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessAuthDto(
                        null,
                        message,
                        LocalDateTime.now()
                ));
    }

    @DeleteMapping("/restart")
    public ResponseEntity<SuccessAuthDto> restartProgress(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Пользователь {} запросил сброс результата", username);

        service.restartUserProgress(username);

        String message = "Прогресс успешно сброшен";

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessAuthDto(
                        null,
                        message,
                        LocalDateTime.now()
                ));
    }
}
