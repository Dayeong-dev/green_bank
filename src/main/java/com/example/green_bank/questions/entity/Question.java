package com.example.green_bank.questions.entity;


import com.example.green_bank.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name="green_questions")
@Setter
@Getter
public class Question extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qno;
    private String title;
    private String content;
    private String isanswered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private QuestionType questionType;
}
