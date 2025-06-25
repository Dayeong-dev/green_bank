package com.example.green_bank.questions.service;

import com.example.green_bank.questions.dto.NotificationDTO;
import com.example.green_bank.questions.entity.Notification;
import com.example.green_bank.questions.repository.NotificationRepository;
import com.example.green_bank.questions.util.NotificationMapper;
import org.springframework.stereotype.Service;

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
}
