package com.example.green_bank.questions.dto;

import com.example.green_bank.questions.entity.QuestionType;
import com.example.green_bank.user.entity.User;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class QuestionDTO {
    private int qno;
    private String title;
    private String content;
    private String isanswered;
    private User user;
    private QuestionType questionType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}