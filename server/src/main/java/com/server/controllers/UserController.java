package com.server.controllers;

import com.server.models.User.User;
import com.server.models.dtos.UserCreationDTO;
import com.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody UserCreationDTO userCreationDTO) {
        return userService.registerUser(userCreationDTO);
    }
}
