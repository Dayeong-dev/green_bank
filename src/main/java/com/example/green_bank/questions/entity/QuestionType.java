package com.example.green_bank.questions.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name="green_questions_type")
@Setter
@Getter
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeid;
    private String name;
}
