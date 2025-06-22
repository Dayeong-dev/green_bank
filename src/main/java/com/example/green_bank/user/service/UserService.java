package com.example.green_bank.user.service;


import com.example.green_bank.user.dto.UserDTO;
import com.example.green_bank.user.entity.User;
import com.example.green_bank.user.repository.UserRepository;
import com.example.green_bank.user.util.EntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    public UserDTO login(UserDTO userDTO){
        User userMapped = entityDtoMapper.toEntity(userDTO);
        User user = userRepository.findByUsernameAndPassword(userMapped.getUsername(), userMapped.getPassword());

        if(user == null) {
            return null;
        }

        return entityDtoMapper.toDTO(user);
    }

    public boolean join(UserDTO userDTO){
        boolean flag = false;
        User userMapped = entityDtoMapper.toEntity(userDTO);

        try {
            userRepository.save(userMapped);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    public boolean checkId(String username){
        return userRepository.existsById(username);//존재하면 true, 없으면 false 반환
    }

}
