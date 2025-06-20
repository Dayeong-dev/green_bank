package com.example.green_bank.repository;

import com.example.green_bank.questions.entity.QuestionType;
import com.example.green_bank.questions.repository.QuestionTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class TestQuestionTypeRepository {
    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Test
    public void setDummies() {
        String[] arr = {"예금", "카드", "대출", "펀드"};

        IntStream.rangeClosed(0, 3).forEach(i -> {
            QuestionType questionType = QuestionType.builder()
                    .name(arr[i])
                    .build();

            questionTypeRepository.save(questionType);
        });
    }

}
