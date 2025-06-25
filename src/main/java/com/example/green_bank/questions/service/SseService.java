package com.example.green_bank.questions.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String username) {
        SseEmitter emitter = new SseEmitter(1000 * 60L);

        emitters.put(username, emitter);

        emitter.onCompletion(() -> emitters.remove(username));
        emitter.onTimeout(() -> emitters.remove(username));

        return emitter;
    }

    public void toSendMessage(String username) {
        SseEmitter emitter = emitters.get(username);

        if(emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("message").data(""));
            } catch (IOException e) {
                emitters.remove(username);
                throw new RuntimeException(e);
            }
        }
    }
}
