package com.kal1van1ch.wordrop.controller;

import com.kal1van1ch.wordrop.model.*;
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
@CrossOrigin(origins = "http://localhost:5173")
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

        if (word == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

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
    public ResponseEntity<SuccessAuthDto> restartProgress(@RequestParam(required = false) WordLevel level){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (level == null){
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

        log.info("Пользователь запросил сброс результат по словам уровня {}", level);

        service.restartUserProgressWithLevel(username, level);

        String message = String.format("Прогресс уровня %s успешно сброшен", level);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessAuthDto(
                        null,
                        message,
                        LocalDateTime.now()
                ));
    }

    @GetMapping("/stat")
    public ResponseEntity<StatDto> getStatInfo(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Пользователь {} запросил данные по статистике своих ответов", username);

        var stat = service.getStatInfo(username);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stat);
    }
}
