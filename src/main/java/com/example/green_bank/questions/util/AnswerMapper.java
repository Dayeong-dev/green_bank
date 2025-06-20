package com.example.green_bank.questions.util;

import com.example.green_bank.questions.dto.AnswerDTO;
import com.example.green_bank.questions.entity.Answer;

public class AnswerMapper {
    public Answer toEntity(AnswerDTO answerDTO) {
        Answer answer = new Answer();

        answer.setAno(answerDTO.getAno());
        answer.setContent(answerDTO.getContent());
        answer.setQuestion(answerDTO.getQuestion());
        answer.setAdmin(answerDTO.getAdmin());

        return answer;
    }

    public AnswerDTO toDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setAno(answer.getAno());
        answerDTO.setContent(answer.getContent());
        answerDTO.setQuestion(answer.getQuestion());
        answerDTO.setAdmin(answer.getAdmin());

        return answerDTO;
    }
}
