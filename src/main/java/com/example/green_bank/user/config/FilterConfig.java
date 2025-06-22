package com.example.green_bank.user.config;

import com.example.green_bank.admin.repository.AdminRepository;
import com.example.green_bank.user.repository.UserRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<SessionFilter> jwtFilter(UserRepository userRepository, AdminRepository adminRepository){
        FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SessionFilter(userRepository, adminRepository));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
