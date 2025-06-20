package com.example.green_bank.questions.repository;

import com.example.green_bank.questions.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
