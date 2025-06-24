package com.example.green_bank.questions.entity;

import com.example.green_bank.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "green_notification")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nno;

    private String message;
    private String targetUrl;
    private Integer isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;
}
