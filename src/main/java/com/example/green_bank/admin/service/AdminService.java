package com.example.green_bank.admin.service;

import com.example.green_bank.admin.dto.AdminDTO;
import com.example.green_bank.admin.entity.Admin;
import com.example.green_bank.admin.repository.AdminRepository;
import com.example.green_bank.admin.util.AdminMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper = new AdminMapper();

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminDTO login(AdminDTO adminDTO){
        Admin result = adminRepository.findByAdminidAndPassword(adminDTO.getAdminid(), adminDTO.getPassword());

        if(result == null) {
            return null;
        }

        return adminMapper.toDTO(result);
    }
}
