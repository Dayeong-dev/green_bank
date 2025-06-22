package com.example.green_bank.questions.controller;

import com.example.green_bank.questions.repository.AnswerRepository;
import com.example.green_bank.questions.entity.Answer;
import com.example.green_bank.questions.entity.Question;
import com.example.green_bank.questions.repository.QuestionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AnswerController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private HttpServletRequest request;

    // 모든 문의 관리
    @GetMapping("/question/all")
    public String allQuestion(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("qnaList", questions); // allQuestion.html에서 qnaList로 사용됨
        return "admin/allQuestion"; // templates/admin/allQuestion.html
    }

    // 답변하기 폼으로 이동
    @GetMapping("/answer")
    public String answer(@RequestParam("id") Integer id, Model model) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            model.addAttribute("question", optionalQuestion.get());
            return "admin/answerQuestion"; // templates/admin/answerQuestion.html
        } else {
            return "redirect:" + getBackUrl(); // 질문이 없으면 목록으로 이동
        }
    }

    // 질문 상세 보기
    @GetMapping("/question/detail")
    public String detailQuestion(@RequestParam("id") Integer id, Model model) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            model.addAttribute("question", optionalQuestion.get());
            return "admin/detailQuestion"; // templates/admin/detailQuestion.html
        } else {
            return "redirect:" + getBackUrl();
        }
    }

    @Transactional
    @PostMapping("/answer/submit")
    public String submitAnswer(@RequestParam("qno") Integer qno,
                               @RequestParam("answer") String content,
                               Model model) {

        Optional<Question> optionalQuestion = questionRepository.findById(qno);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            // 1. Answer 엔티티 생성 및 저장
            Answer answer = Answer.builder()
                    .content(content)
                    .question(question)
                    //.admin(admin)
                    .build();

            answerRepository.save(answer);

            question.setIsanswered("1");
            questionRepository.save(question);
        }

        return "redirect:/admin/question/detail?id=" + qno;  // 등록 후 상세로 이동
    }

    public String getBackUrl() {
        String referer = request.getHeader("referer");

        if(referer == null)  {
            return "/admin";
        }

        return referer;
    }

}

