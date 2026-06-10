package com.kal1van1ch.wordrop.repository;

import com.kal1van1ch.wordrop.model.Word;
import com.kal1van1ch.wordrop.model.WordLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("select w from Word w where w.level = :level and w.id not in (select u.word.id from UserWordHistory u where u.user.id = :userId and u.isCorrect = true)")
    List<Word> findUnansweredWordsByLevel(
            @Param("level")WordLevel level,
            @Param("userId") Long userId
    );
}
