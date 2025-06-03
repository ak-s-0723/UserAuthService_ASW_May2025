package org.example.userauthservice_asw_may2025.controllers;

import org.example.userauthservice_asw_may2025.dtos.LoginRequestDto;
import org.example.userauthservice_asw_may2025.dtos.SignupRequestDto;
import org.example.userauthservice_asw_may2025.dtos.UserDto;
import org.example.userauthservice_asw_may2025.exceptions.PasswordMismatchException;
import org.example.userauthservice_asw_may2025.exceptions.UserAlreadySignedException;
import org.example.userauthservice_asw_may2025.exceptions.UserNotRegisteredException;
import org.example.userauthservice_asw_may2025.models.User;
import org.example.userauthservice_asw_may2025.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        User user = authService.signup(signupRequestDto.getName(),signupRequestDto.getEmail(),signupRequestDto.getPassword());
        return from(user);
    }

    @PostMapping("/login")
    UserDto login(@RequestBody LoginRequestDto loginRequestDto) {
       User user =  authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
       return from(user);
    }

    private UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}

