package com.zportia.services;

import com.zportia.model.User;
import com.zportia.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void encodePasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String rawPassword = user.getPassword();
            if (!rawPassword.startsWith("$2a$")) {
                user.setPassword(encoder.encode(rawPassword));
                userRepository.save(user);
            }
        }
    }


}
