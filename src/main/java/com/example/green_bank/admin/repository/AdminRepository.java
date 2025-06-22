package com.example.green_bank.admin.repository;

import com.example.green_bank.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByAdminidAndPassword(String adminid, String password);
}
