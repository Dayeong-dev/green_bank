package com.example.green_bank.questions.dto;

import com.example.green_bank.user.dto.UserDTO;
import com.example.green_bank.user.entity.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Integer nno;
    private String message;
    private String targetUrl;
    private Integer isRead;
    private UserDTO userDTO;
}
