package com.example.green_bank.admin.controller;

import com.example.green_bank.admin.dto.AdminDTO;
import com.example.green_bank.admin.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/loginForm")
    public String root(){
        return "admin/adminLogin";
    }

    @PostMapping("/login")
    public String login(AdminDTO adminDTO, HttpSession session, RedirectAttributes rttr){
        AdminDTO admin = adminService.login(adminDTO);

        if(admin == null){
            String msg = "아이디 및 비밀번호를 확인해주세요";
            rttr.addFlashAttribute("msg", msg);

            return "redirect:/admin/loginForm";
        }

        session.setAttribute("adminId", admin.getAdminid());
        session.setAttribute("adminName", admin.getName());

        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");

        if(adminId != null) {
            session.removeAttribute("adminId");
            session.removeAttribute("adminName");
        }

        return "redirect:/admin/loginForm";
    }
}
