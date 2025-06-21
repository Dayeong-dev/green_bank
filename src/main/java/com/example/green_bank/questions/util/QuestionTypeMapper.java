package com.example.green_bank.questions.util;

import com.example.green_bank.questions.dto.QuestionTypeDTO;
import com.example.green_bank.questions.entity.QuestionType;

public class QuestionTypeMapper {
    public QuestionType toEntity(QuestionTypeDTO questionTypeDTO) {
        QuestionType questionType = new QuestionType();

        questionType.setTypeid(questionTypeDTO.getTypeid());
        questionType.setName(questionTypeDTO.getName());

        return questionType;
    }

    public QuestionTypeDTO toDTO(QuestionType questionType) {
        QuestionTypeDTO questionTypeDTO = new QuestionTypeDTO();

        questionTypeDTO.setTypeid(questionType.getTypeid());
        questionTypeDTO.setName(questionType.getName());

        return questionTypeDTO;
    }
}
