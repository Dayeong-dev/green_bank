package com.example.green_bank.repository;

import com.example.green_bank.questions.entity.Question;
import com.example.green_bank.questions.repository.QuestionRepository;
import com.example.green_bank.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class TestQuestionRepository {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void setDummies(){
        User user = new User();
        user.setUsername("user01");

        IntStream.rangeClosed(0, 9).forEach(i -> {
            Question question = Question.builder()
                    .title("문의드립니다" + i)
                    .content("오늘은 비가 올 예정이에요")
                    .user(user)
                    .isanswered("0")
                    .build();

            questionRepository.save(question);

        });



    }
}
