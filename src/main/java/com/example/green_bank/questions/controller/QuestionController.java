package com.example.green_bank.questions.controller;

import com.example.green_bank.questions.dto.QuestionAnswerDTO;
import com.example.green_bank.questions.dto.QuestionDTO;
import com.example.green_bank.questions.dto.QuestionTypeDTO;
import com.example.green_bank.questions.service.QuestionService;
import com.example.green_bank.questions.service.QuestionTypeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionTypeService questionTypeService;

    public QuestionController(QuestionService questionService, QuestionTypeService questionTypeService) {
        this.questionService = questionService;
        this.questionTypeService = questionTypeService;
    }

    // 사용자 - 메인
    @GetMapping("/")
    public String main() {
        System.out.println("User Main......");

        return "main";
    }

    // 사용자 - 문의 화면으로 이동
    @GetMapping("/questionForm/{typeId}")
    public String questionForm(@PathVariable("typeId") Integer typeId, Model model) {
        QuestionTypeDTO questionTypeDTO = questionTypeService.getQuestionType(typeId);

        if(questionTypeDTO == null) {   // 관련된 문의 유형이 없음
            return "redirect:/";
        }

        model.addAttribute("typeId", questionTypeDTO.getTypeid());
        model.addAttribute("typeName", questionTypeDTO.getName());

        return "questionForm";
    }

    // 사용자 - 문의
    @PostMapping("/regQuestion/{typeId}")
    public String regQuestion(@PathVariable("typeId") Integer typeId, QuestionDTO questionDTO, HttpSession session, RedirectAttributes rttr) {
        String username = (String) session.getAttribute("username");

        boolean result = questionService.regQuestion(questionDTO, typeId, username);

        if(!result) {
            rttr.addFlashAttribute("errorMsg", "문의를 보내는 중 문제가 발생했습니다. ");
            return "redirect:/questionForm/" + typeId;
        }

        return "redirect:/regSuccess";
    }

    // 사용자 - 문의 완료 화면으로 이동
    @GetMapping("/regSuccess")
    public String regSuccess() {
        return "regSuccess";
    }

    // 사용자 - 마이페이지로 이동
    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        List<QuestionDTO> questionList = questionService.getQuestionsByUsername(username);
        model.addAttribute("questionList", questionList);

        return "myPage";
    }

    // 사용자 - 문의 상세로 이동
    @GetMapping("/questionDetail/{qno}")
    public String questionDetail(@PathVariable("qno") Integer qno, Model model) {
        QuestionAnswerDTO questionAnswerDTO = questionService.getQuestionAnswerByQno(qno);

        if(questionAnswerDTO == null) {
            return "redirect:/";
        }

        model.addAttribute("question", questionAnswerDTO.getQuestionDTO());
        model.addAttribute("answer", questionAnswerDTO.getAnswerDTO());

        return "questionDetail";
    }

    // 사용자 - 문의 수정으로 이동
    @GetMapping("/updateForm/{qno}")
    public String updateForm(@PathVariable("qno") Integer qno, Model model) {
        QuestionDTO questionDTO = questionService.getQuestionByQno(qno);

        if(questionDTO == null) {
            return "redirect:/";
        }

        model.addAttribute("question", questionDTO);

        return "updateForm";
    }

    // 사용자 - 문의 수정
    @PostMapping("/updateQuestion/{qno}")
    public String updateQuestion(@PathVariable("qno") Integer qno, QuestionDTO questionDTO) {
        boolean result = questionService.updateQuestion(qno, questionDTO);

        if(!result) {
            return "redirect:/updateForm/" + qno;
        }

        return "redirect:/questionDetail/" + qno;
    }

    // 사용자 - 문의 삭제
    @GetMapping("/deleteQuestion/{qno}")
    public String deleteQuestion(@PathVariable("qno") Integer qno) {
        boolean result = questionService.deleteQuestion(qno);

        if(!result) {
            return "redirect:/questionDetail/" + qno;
        }

        return "redirect:/myPage";
    }

    /* ------------------------------ */

    // 관리자 - 메인
    @GetMapping({"/admin", "/admin/"})
    public String adminMain(HttpSession session, Model model) {
        System.out.println("Admin Main......");

        String adminId = (String) session.getAttribute("adminId");

        List<QuestionDTO> questionList = questionService.getQuestionsByTypeIdAndIsanswered(adminId);
        for(QuestionDTO questionDTO : questionList) {
            System.out.println("question: " + questionDTO);
        }

        model.addAttribute("questionList", questionList);

        return "admin/main";
    }
}