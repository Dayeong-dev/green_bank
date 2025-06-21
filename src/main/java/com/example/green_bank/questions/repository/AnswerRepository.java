package com.example.green_bank.questions.repository;

import com.example.green_bank.questions.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Answer findByQuestion_Qno(Integer qno);
}
