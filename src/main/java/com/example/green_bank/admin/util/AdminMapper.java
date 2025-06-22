package com.example.green_bank.admin.util;

import com.example.green_bank.admin.dto.AdminDTO;
import com.example.green_bank.admin.entity.Admin;

public class AdminMapper {

    public Admin toEntity(AdminDTO adminDTO) {
        Admin admin = new Admin();

        admin.setAdminid(adminDTO.getAdminid());
        admin.setPassword(adminDTO.getPassword());
        admin.setName(adminDTO.getName());
        admin.setQuestionType(adminDTO.getQuestionType());

        return admin;
    }

    public AdminDTO toDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setAdminid(admin.getAdminid());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setName(admin.getName());
        adminDTO.setQuestionType(admin.getQuestionType());

        return adminDTO;
    }
}
