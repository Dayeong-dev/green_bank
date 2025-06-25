package com.example.green_bank.questions.util;

import com.example.green_bank.questions.dto.NotificationDTO;
import com.example.green_bank.questions.entity.Notification;

public class NotificationMapper {
    public Notification toEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();

        notification.setNno(notificationDTO.getNno());
        notification.setMessage(notificationDTO.getMessage());
        notification.setTargetUrl(notificationDTO.getTargetUrl());
        notification.setIsRead(notificationDTO.getIsRead());
        notification.setUser(notificationDTO.getUser());

        return notification;
    }

    public NotificationDTO toDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setNno(notification.getNno());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setTargetUrl(notification.getTargetUrl());
        notificationDTO.setIsRead(notification.getIsRead());
        notificationDTO.setUser(notification.getUser());

        return notificationDTO;
    }
}
