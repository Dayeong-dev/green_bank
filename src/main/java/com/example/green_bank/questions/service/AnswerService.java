package com.example.green_bank.questions.service;

import com.example.green_bank.admin.entity.Admin;
import com.example.green_bank.questions.dto.NotificationDTO;
import com.example.green_bank.questions.entity.Answer;
import com.example.green_bank.questions.entity.Question;
import com.example.green_bank.questions.repository.AnswerRepository;
import com.example.green_bank.questions.repository.QuestionRepository;
import com.example.green_bank.questions.util.AnswerMapper;
import com.example.green_bank.user.util.EntityDtoMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final NotificationService notificationService;
    private final SseService sseService;

    private final AnswerMapper answerMapper = new AnswerMapper();
    private final EntityDtoMapper userMapper = new EntityDtoMapper();

    public AnswerService(AnswerRepository answerRepository,
                         QuestionRepository questionRepository,
                         NotificationService notificationService,
                         SseService sseService) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.notificationService = notificationService;
        this.sseService = sseService;
    }

    @Transactional
    public boolean regAnswer(Integer qno, String adminId, String content) {
        Optional<Question> optionalQuestion = questionRepository.findById(qno);

        if (optionalQuestion.isEmpty()) {
            return false;
        }

        Question question = optionalQuestion.get();
        Admin admin = new Admin();

        admin.setAdminid(adminId);

        // Answer 엔티티 생성 및 저장
        Answer answer = Answer.builder()
                .content(content)
                .question(question)
                .admin(admin)
                .build();

        answerRepository.save(answer);

        // 질문 테이블의 답변여부를 1로 변경
        question.setIsanswered("1");
        questionRepository.save(question);

        // 알림 테이블에 메시지 추가
        String message = "[" + question.getTitle() + "] 문의에 답변이 등록되었습니다. ";
        String targetUrl = "/questionDetail/" + qno;

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage(message);
        notificationDTO.setTargetUrl(targetUrl);
        notificationDTO.setIsRead(0);
        notificationDTO.setUserDTO(userMapper.toDTO(question.getUser()));

        notificationService.regNotification(notificationDTO);

        // 해당 사용자에게 알림 event 전송
        sseService.toSendMessage(question.getUser().getUsername());

        return true;
    }
}
