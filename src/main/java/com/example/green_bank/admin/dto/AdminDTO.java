package com.example.green_bank.admin.dto;

import com.example.green_bank.questions.entity.QuestionType;
import lombok.Data;

@Data
public class AdminDTO {
    private String adminid;
    private String password;
    private String name;
    private QuestionType questionType;
}
