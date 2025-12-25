package com.pm.authservice.service;

import com.pm.authservice.User;
import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    public Optional<User> findByEmail(String email){

        return userRepo.findByEmail(email);
    }
}
