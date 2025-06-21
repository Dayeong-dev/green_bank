package com.example.green_bank.admin.repository;

import com.example.green_bank.admin.dto.AnswerDto;
import com.example.green_bank.questions.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Answer findByQuestion_Qno(Integer qno);
}
