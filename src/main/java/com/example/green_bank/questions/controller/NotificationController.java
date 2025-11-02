package com.example.green_bank.questions.controller;

import com.example.green_bank.questions.dto.NotificationDTO;
import com.example.green_bank.questions.service.NotificationService;
import com.example.green_bank.questions.service.SseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NotificationController {
    private final NotificationService notificationService;
    private final SseService sseService;

    public NotificationController(NotificationService notificationService, SseService sseService) {
        this.notificationService = notificationService;
        this.sseService = sseService;
    }

    @GetMapping("/subscribe")
    public SseEmitter subscribe(HttpSession session) {
        String username = (String) session.getAttribute("username");
        SseEmitter sseEmitter = sseService.subscribe(username);

        return sseEmitter;
    }

    @GetMapping("/notification/read")
    public List<NotificationDTO> getNotification(HttpSession session) {	
        String username = (String) session.getAttribute("username");
        List<NotificationDTO> notificationList = notificationService.readNotifications(username);

        return notificationList;
    }
    
    @GetMapping("/notification/has-unread")
    public Map<String, Boolean> hasUnread(HttpSession session) {
    	String username = (String) session.getAttribute("username");
    	Boolean result = notificationService.hasUnreadNotification(username);
    	
    	Map<String, Boolean> map = new HashMap<>();
    	map.put("hasUnread", result);
    	
    	return map;
    }
}
