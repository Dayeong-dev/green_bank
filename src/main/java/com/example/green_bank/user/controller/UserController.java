package com.example.green_bank.user.controller;

import com.example.green_bank.user.dto.UserDTO;
import com.example.green_bank.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/loginForm")
    public String root(){
        return "index";
    }

    @GetMapping("/joinForm")
    public String joinPg(){
        return "joinPg";
    }

    @PostMapping("/join/checkId")
    @ResponseBody
    public String checkId(@RequestParam("username")String username){
        String msg = "";
        if(!userService.checkId(username)){
            msg = "사용가능한 아이디 입니다";
        }else{
            msg ="중복된 아이디 입니다";
        }
        return msg;
    }

    @PostMapping("/join")
    public String join(UserDTO userDTO, RedirectAttributes rttr){
        String msg = "";

        if(userService.join(userDTO)){
            msg = "회원가입이 완료되었습니다";
            rttr.addFlashAttribute("msg", msg);
            return "redirect:/loginForm";
        }else{
            msg ="다시 진행해주세요";
            rttr.addFlashAttribute("msg", msg);
        }
        return "redirect:/joinForm";
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session, RedirectAttributes rttr){
        String msg = "";
        UserDTO user = userService.login(userDTO);

        if(user != null){
            session.setAttribute("username", user.getUsername());
            session.setAttribute("name", user.getName());
            return "redirect:/";
        }else{
            msg = "아이디 및 비밀번호를 확인해주세요";
            rttr.addFlashAttribute("msg", msg);
        }
        return "redirect:/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if(username != null) {
            session.removeAttribute("username");
            session.removeAttribute("name");
        }

        return "redirect:/loginForm";
    }

}
