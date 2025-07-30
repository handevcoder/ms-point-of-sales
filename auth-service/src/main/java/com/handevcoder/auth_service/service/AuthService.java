package com.handevcoder.auth_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.handevcoder.auth_service.entity.Users;
import com.handevcoder.auth_service.model.LoginRequest;
import com.handevcoder.auth_service.repository.UserRepository;
import com.handevcoder.auth_service.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder encoder;
    @SuppressWarnings("unused")
    @Autowired
    private RedisService redisSevice;

    public String login(LoginRequest req) {
        Users user = userRepo.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("Invalid User"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return jwtUtil.generateToken(req.getUsername());
    }

    public String register(LoginRequest req) {
        Optional<Users> users = userRepo.findByUsername(req.getUsername());
      
        if (users.isPresent()) {
            return "Username already exists";
        }
        Users user = new Users();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        userRepo.save(user);
        return "User Registered";
    }

}
