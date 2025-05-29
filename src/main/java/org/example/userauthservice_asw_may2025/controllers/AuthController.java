package org.example.userauthservice_asw_may2025.controllers;

import org.example.userauthservice_asw_may2025.dtos.LoginRequestDto;
import org.example.userauthservice_asw_may2025.dtos.SignupRequestDto;
import org.example.userauthservice_asw_may2025.dtos.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        //It will call service layer method
        return null;
    }

    @PostMapping("/login")
    UserDto login(@RequestBody LoginRequestDto loginRequestDto) {
        //It will call service layer
        return null;
    }
}

