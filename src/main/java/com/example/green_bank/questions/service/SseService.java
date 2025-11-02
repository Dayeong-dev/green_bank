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
    	SseEmitter oldEmitter = emitters.get(username);
    	
    	if(oldEmitter != null) {
    		try {
    			// 이전 Emitter 닫아주기
    			// Emitter는 하나의 HTTP 응답 스트림에 묶이기 때문에 이전 건 닫고 새로 보내줘야함
    			oldEmitter.complete();
    		} catch(Exception e) {
    			// 예외는 무시
    		}
    	}
    	
        SseEmitter emitter = new SseEmitter(30 * 60 * 1000L);	// 타임아웃 30분으로 지정

        emitters.put(username, emitter);

        emitter.onCompletion(() -> emitters.remove(username));
        emitter.onTimeout(() -> emitters.remove(username));
        emitter.onError(e -> emitters.remove(username));

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
