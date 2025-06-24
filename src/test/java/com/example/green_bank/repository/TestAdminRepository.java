package com.example.green_bank.repository;

import com.example.green_bank.admin.entity.Admin;
import com.example.green_bank.admin.repository.AdminRepository;
import com.example.green_bank.questions.entity.QuestionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class TestAdminRepository {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void setAdminDummy() {
        IntStream.rangeClosed(1, 4).forEach(i -> {
            QuestionType questionType = new QuestionType();
            questionType.setTypeid(i);

            Admin admin = Admin.builder()
                    .adminid("admin" + i)
                    .name("손소희" + i)
                    .questionType(questionType)
                    .password("1234")
                    .build();

            adminRepository.save(admin);
        });
    }

}
