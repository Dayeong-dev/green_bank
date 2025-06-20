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

    @GetMapping("/main")
    public String main(HttpSession session) {
        System.out.println("root......");

        session.setAttribute("username", "user01");

        return "main";
    }

    @GetMapping("/questionForm/{typeId}")
    public String questionForm(@PathVariable("typeId") Integer typeId, Model model) {
        QuestionTypeDTO questionTypeDTO = questionTypeService.getQuestionType(typeId);

        if(questionTypeDTO == null) {   // 관련된 문의 유형이 없음
            return "redirect:/main";
        }

        model.addAttribute("typeId", questionTypeDTO.getTypeid());
        model.addAttribute("typeName", questionTypeDTO.getName());

        return "questionForm";
    }

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

    @GetMapping("/regSuccess")
    public String regSuccess() {
        return "regSuccess";
    }

    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        List<QuestionDTO> questionList = questionService.getQuestionsByUsername(username);
        model.addAttribute("questionList", questionList);

        return "myPage";
    }

    @GetMapping("/questionDetail/{qno}")
    public String questionDetail(@PathVariable("qno") Integer qno, Model model) {
        QuestionAnswerDTO questionAnswerDTO = questionService.getQuestionAnswerByQno(qno);

        if(questionAnswerDTO == null) {
            return "redirect:/main";
        }

        model.addAttribute("question", questionAnswerDTO.getQuestionDTO());
        model.addAttribute("answer", questionAnswerDTO.getAnswerDTO());

        return "questionDetail";
    }

    @GetMapping("/updateForm/{qno}")
    public String updateForm(@PathVariable("qno") Integer qno, Model model) {
        QuestionDTO questionDTO = questionService.getQuestionByQno(qno);
        model.addAttribute("question", questionDTO);

        return "updateForm";
    }

    @PostMapping("/updateQuestion/{qno}")
    public String updateQuestion(@PathVariable("qno") Integer qno, QuestionDTO questionDTO) {
        System.out.println(qno);
        System.out.println(questionDTO);

        return "redirect:/questionDetail/" + qno;
    }

    @GetMapping("/deleteQuestion/{qno}")
    public String deleteQuestion(@PathVariable("qno") Integer qno) {
        questionService.deleteQuestion(qno);

        return "redirect:/myPage";
    }
}