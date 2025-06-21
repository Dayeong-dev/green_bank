package com.example.green_bank.questions.util;

import com.example.green_bank.questions.dto.QuestionDTO;
import com.example.green_bank.questions.entity.Question;

public class QuestionMapper {
    public Question toEntity(QuestionDTO questionDTO) {
        Question question = new Question();

        question.setQno(questionDTO.getQno());
        question.setTitle(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());
        question.setIsanswered(questionDTO.getIsanswered());
        question.setUser(questionDTO.getUser());
        question.setQuestionType(questionDTO.getQuestionType());

        return question;
    }

    public QuestionDTO toDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setQno(question.getQno());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setContent(question.getContent());
        questionDTO.setIsanswered(question.getIsanswered());
        questionDTO.setUser(question.getUser());
        questionDTO.setQuestionType(question.getQuestionType());
        questionDTO.setCreatedAt(question.getCreatedAt());
        questionDTO.setModifiedAt(question.getModifiedAt());

        return questionDTO;
    }
}
