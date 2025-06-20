package com.example.green_bank.user.controller;

import com.example.green_bank.user.dto.UserDTO;
import com.example.green_bank.user.entity.User;
import com.example.green_bank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String root(){
        return "index";
    }

    @GetMapping("/joinPg")
    public String joinPg(){
        return "joinPg";
    }

    @PostMapping("/checkId")
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
            return "index";
        }else{
            msg ="다시 진행해주세요";
            rttr.addFlashAttribute("msg", msg);
        }
        return "joinPg";
    }

    @GetMapping("/loginPg")
    public String loginPg(){
        return "index";
    }

    @PostMapping("login")
    public String login(UserDTO userDTO, RedirectAttributes rttr){
        String msg = "";
        if(userService.login(userDTO)){
            msg = userDTO.getName() + "님 환영합니다!";
            rttr.addFlashAttribute("msg", msg);
            return "main";
        }else{
            msg = "아이디 및 비밀번호를 확인해주세요";
            rttr.addFlashAttribute("msg", msg);
        }
        return "loginPg";
    }
}
