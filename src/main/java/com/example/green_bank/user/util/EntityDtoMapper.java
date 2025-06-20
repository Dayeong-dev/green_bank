package com.example.green_bank.user.util;


import com.example.green_bank.user.dto.UserDTO;
import com.example.green_bank.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getPassword());
        userDTO.setPhone(user.getPhone());

        return userDTO;
    }

    public User toEntity(UserDTO userDTO){
        User user = new User();

        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setUsername(userDTO.getUsername());

        return user;
    }
}
