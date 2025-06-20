package com.example.green_bank.admin.entity;


import com.example.green_bank.questions.entity.QuestionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name="green_admin")
@Setter
@Getter
public class Admin {
    @Id
    private String adminid;
    private String password;
    private String name;


}
