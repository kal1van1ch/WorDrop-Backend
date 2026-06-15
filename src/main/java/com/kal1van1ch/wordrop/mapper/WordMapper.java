package com.kal1van1ch.wordrop.mapper;

import com.kal1van1ch.wordrop.model.dto.WordDto;
import com.kal1van1ch.wordrop.model.entity.Word;
import org.springframework.stereotype.Component;

@Component
public class WordMapper {
    public WordDto toDto(Word word){
        return new WordDto(
                word.getId(),
                word.getWord(),
                word.getTranslation(),
                word.getLevel()
        );
    }

    public Word toEntity(WordDto word){
        return new Word(
                word.id(),
                word.word(),
                word.translation(),
                word.level()
        );
    }
}
