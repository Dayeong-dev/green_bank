package com.example.green_bank.questions.dto;

import com.example.green_bank.admin.entity.Admin;
import com.example.green_bank.questions.entity.Question;
import lombok.Data;

@Data
public class AnswerDTO {
    private int ano;
    private String content;
    private Question question;
    private Admin admin;
}
