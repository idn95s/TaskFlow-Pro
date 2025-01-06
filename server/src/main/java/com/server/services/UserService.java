package com.server.services;

import com.server.models.User.User;
import com.server.models.dtos.UserCreationDTO;
import com.server.repositories.UserRepository;
import com.server.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setEmail(userCreationDTO.getEmail());
        user.setPasswordHash(PasswordUtil.encodePassword(userCreationDTO.getPassword()));
        return userRepository.save(user);
    }
}
