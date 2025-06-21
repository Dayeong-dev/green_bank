package com.example.green_bank.questions.service;

import com.example.green_bank.questions.dto.QuestionTypeDTO;
import com.example.green_bank.questions.entity.QuestionType;
import com.example.green_bank.questions.repository.QuestionTypeRepository;
import com.example.green_bank.questions.util.QuestionTypeMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionTypeService {
    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionTypeMapper questionTypeMapper = new QuestionTypeMapper();

    public QuestionTypeService(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    public QuestionTypeDTO getQuestionType(Integer typeId) {
        Optional<QuestionType> result = questionTypeRepository.findById(typeId);

        if(result.isEmpty()) {
            System.out.println("해당 문의 유형이 없습니다. ");
            return null;
        }
        QuestionType questionType = result.get();

        return questionTypeMapper.toDTO(questionType);
    }
}
