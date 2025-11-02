package com.example.green_bank.questions.service;

import com.example.green_bank.questions.dto.NotificationDTO;
import com.example.green_bank.questions.entity.Notification;
import com.example.green_bank.questions.repository.NotificationRepository;
import com.example.green_bank.questions.util.NotificationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper = new NotificationMapper();

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationDTO regNotification(NotificationDTO notificationDTO) {
        if(notificationDTO == null) {
            return null;
        }

        Notification notification = notificationMapper.toEntity(notificationDTO);
        Notification result = notificationRepository.save(notification);

        return notificationMapper.toDTO(result);
    }

    @Transactional
    public List<NotificationDTO> readNotifications(String username) {
    	LocalDateTime since = LocalDateTime.now().minusDays(30);
    	
        List<Notification> result = notificationRepository.findRecentNotificationByUsername(username, since);
        List<NotificationDTO> notificationList = new ArrayList<>();

        for(Notification notification : result) {
        	// notification 객체를 DTO로 저장
            notificationList.add(notificationMapper.toDTO(notification));
            
            // 조회된 notification 읽음 처리
            notification.setIsRead(1);
            notificationRepository.save(notification);
        }

        return notificationList;
    }

	public boolean hasUnreadNotification(String username) {
		boolean result = notificationRepository.existsByUser_UsernameAndIsRead(username, 0);
		
		return result;
	}
}
