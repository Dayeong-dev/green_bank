package com.example.green_bank.questions.controller;

import com.example.green_bank.questions.dto.NotificationDTO;
import com.example.green_bank.questions.service.NotificationService;
import com.example.green_bank.questions.service.SseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

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
        System.out.println("Hello World");

        String username = (String) session.getAttribute("username");
        SseEmitter sseEmitter = sseService.subscribe(username);

        return sseEmitter;
    }
}
