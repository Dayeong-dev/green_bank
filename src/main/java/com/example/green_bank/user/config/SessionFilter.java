package com.example.green_bank.user.config;


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

    @Autowired
    private UserRepository userRepository;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println("hello" + uri);

        if (uri.startsWith("/login") || uri.startsWith("/join") || uri.equals("/") || uri.startsWith("/images") || uri.equals("/checkId")) {
            chain.doFilter(request, response); // 필터 통과
            return;
        }
        System.out.println("필터작동!!!!");
        HttpSession session = req.getSession();

        String username = (String) session.getAttribute("username");

        if (username == null || username.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            Optional<User> findUser = userRepository.findById(username);

            if (findUser.isPresent()) {
                User user = findUser.get();
                req.setAttribute("username", user.getUsername());
                chain.doFilter(request, response);
            }
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;


        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }
}
