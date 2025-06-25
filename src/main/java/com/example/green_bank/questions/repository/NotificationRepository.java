package com.example.green_bank.questions.repository;

import com.example.green_bank.questions.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
