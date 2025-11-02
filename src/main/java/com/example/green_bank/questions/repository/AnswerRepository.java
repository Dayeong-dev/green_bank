package com.example.green_bank.questions.repository;

import com.example.green_bank.questions.entity.Answer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Optional<Answer> findByQuestion_Qno(Integer qno);
}
