package com.example.green_bank.user.config;


import com.example.green_bank.admin.entity.Admin;
import com.example.green_bank.admin.repository.AdminRepository;
import com.example.green_bank.user.entity.User;
import com.example.green_bank.user.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class SessionFilter implements Filter {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public SessionFilter(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        System.out.println("Uri: " + uri);

        // 정적 자원과 인터넷 표준 경로는 필터 통과
        if(uri.startsWith("/css") || uri.startsWith("/images") || uri.startsWith("/js") || uri.startsWith("/.well-known")) {
            chain.doFilter(request, response); // 필터 통과
            return;
        }

        // 사용자 로그인/회원가입은 필터 통과
        if(uri.startsWith("/login") || uri.startsWith("/join")) {
            chain.doFilter(request, response);
            return;
        }

        // 관리자 로그인은 필터 통과
        if(uri.startsWith("/admin/login")) {
            chain.doFilter(request, response);
            return;
        }


        System.out.println("필터작동!!!!");

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String adminId = (String) session.getAttribute("adminId");

        // 세션에 사용자 계정 有
        if (username != null) {
            try {
                Optional<User> findUser = userRepository.findById(username);

                if (findUser.isPresent()) {
                    System.out.println("username: " + username);
                    User user = findUser.get();
                    req.setAttribute("username", user.getUsername());
                    chain.doFilter(request, response);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error: 유효하지 않은 username 입니다.");
                System.out.println(e.toString());
            }
        }

        // 세션에 관리자 계정 有
        if(adminId != null) {
            try {
                Optional<Admin> findAdmin = adminRepository.findById(adminId);

                if (findAdmin.isPresent()) {
                    System.out.println("Admin Id: " + adminId);
                    Admin admin = findAdmin.get();
                    req.setAttribute("adminId", admin.getAdminid());
                    chain.doFilter(request, response);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error: 유효하지 않은 Admin Id 입니다.");
                System.out.println(e.toString());
            }
        }

        // 관리자 페이지 접근 시 관리자 로그인 폼으로 redirect
        if(uri.startsWith("/admin")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/admin/loginForm");
            return;
        }

        // 사용자 로그인 폼으로 redirect
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.sendRedirect("/loginForm");
    }
}
