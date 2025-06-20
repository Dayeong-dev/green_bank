package com.example.green_bank.questions.entity;


import com.example.green_bank.admin.entity.Admin;
import com.example.green_bank.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name="green_answers")
@Setter
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ano;
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qno")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
