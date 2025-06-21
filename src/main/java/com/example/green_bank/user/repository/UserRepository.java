package com.example.green_bank.user.repository;

import com.example.green_bank.user.dto.UserDTO;
import com.example.green_bank.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    public User findByUsernameAndPassword(String username, String password);
}
