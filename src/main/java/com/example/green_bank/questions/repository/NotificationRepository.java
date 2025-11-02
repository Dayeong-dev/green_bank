package com.example.green_bank.questions.repository;

import com.example.green_bank.questions.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	@Query("select n from Notification n where n.user.username = :username and (n.createdAt >= :since or n.isRead = 0) order by n.createdAt desc")
    List<Notification> findRecentNotificationByUsername(@Param("username") String username, @Param("since") LocalDateTime since);
}
