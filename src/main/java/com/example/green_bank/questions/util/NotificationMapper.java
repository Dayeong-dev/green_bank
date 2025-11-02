package com.example.green_bank.questions.util;

import com.example.green_bank.questions.dto.NotificationDTO;
import com.example.green_bank.questions.entity.Notification;
import com.example.green_bank.user.util.EntityDtoMapper;

public class NotificationMapper {
	
	private EntityDtoMapper userMapper = new EntityDtoMapper();
	
    public Notification toEntity(NotificationDTO notificationDTO) {
    	
    	if(notificationDTO == null) return null;
    	
        Notification notification = new Notification();

        notification.setNno(notificationDTO.getNno());
        notification.setMessage(notificationDTO.getMessage());
        notification.setTargetUrl(notificationDTO.getTargetUrl());
        notification.setIsRead(notificationDTO.getIsRead());
        notification.setUser(userMapper.toEntity(notificationDTO.getUserDTO()));

        return notification;
    }

    public NotificationDTO toDTO(Notification notification) {
    	
    	if(notification == null) return null;
    	
        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setNno(notification.getNno());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setTargetUrl(notification.getTargetUrl());
        notificationDTO.setIsRead(notification.getIsRead());
        notificationDTO.setUserDTO(userMapper.toDTO(notification.getUser()));

        return notificationDTO;
    }
}
