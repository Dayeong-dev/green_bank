package com.example.green_bank.questions.repository;

import com.example.green_bank.questions.entity.Question;
import com.example.green_bank.questions.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByUser_Username(String username);

    List<Question> findAllByQuestionType_TypeidAndIsanswered(Integer typeId, String isanswered);
}
