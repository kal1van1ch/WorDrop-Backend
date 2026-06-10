package com.kal1van1ch.wordrop.service;

import com.kal1van1ch.wordrop.model.User;
import com.kal1van1ch.wordrop.model.UserWordHistory;
import com.kal1van1ch.wordrop.model.Word;
import com.kal1van1ch.wordrop.model.WordLevel;
import com.kal1van1ch.wordrop.repository.UserRepository;
import com.kal1van1ch.wordrop.repository.UserWordHistoryRepository;
import com.kal1van1ch.wordrop.repository.WordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class WordService {
    private final UserRepository userRepository;
    private final UserWordHistoryRepository userWordHistoryRepository;
    private final WordRepository wordRepository;
    private final Random random = new Random();

    public WordService(
            UserRepository userRepository,
            UserWordHistoryRepository userWordHistoryRepository,
            WordRepository wordRepository
    ){
        this.userRepository = userRepository;
        this.userWordHistoryRepository = userWordHistoryRepository;
        this.wordRepository = wordRepository;
    }

    public Word getRandomWord(WordLevel level, String username){
        User user = userRepository.findByUsername(username);
        List<Word> availableWords = wordRepository.findUnansweredWordsByLevel(level, user.getId());

        if(availableWords.isEmpty()){
            userWordHistoryRepository.deleteByUserId(user.getId());
            availableWords = wordRepository.findUnansweredWordsByLevel(level, user.getId());
        }

        Word word = availableWords.get(
                random.nextInt(availableWords.size())
        );

        return word;
    }

    public void logAnswerResult(boolean isCorrect, Long wordId, String username){
        User user = userRepository.findByUsername(username);
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new EntityNotFoundException("Такого слова нет в базе"));

        UserWordHistory history = new UserWordHistory();
        history.setUser(user);
        history.setCorrect(isCorrect);
        history.setAnswerAt(LocalDateTime.now());
        history.setWord(word);

        userWordHistoryRepository.save(history);
    }

    public void restartUserProgress(String username){
        User user = userRepository.findByUsername(username);
        userWordHistoryRepository.deleteByUserId(user.getId());
    }
}
