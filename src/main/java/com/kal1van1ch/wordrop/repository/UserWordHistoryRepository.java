package com.kal1van1ch.wordrop.repository;

import com.kal1van1ch.wordrop.model.UserWordHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserWordHistoryRepository extends JpaRepository<UserWordHistory, Long> {
    @Modifying
    @Transactional
    @Query("delete from UserWordHistory u where u.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

    @Query("select count(u) from UserWordHistory u where u.user.id = :userId and u.isCorrect = true")
    Long countCorrectWordsByUserId(@Param("userId") Long userId);

    @Query("select count(u) from UserWordHistory u where u.user.id = :userId and u.isCorrect = false")
    Long countWrongWordsByUserId(@Param("userId") Long userId);
}
