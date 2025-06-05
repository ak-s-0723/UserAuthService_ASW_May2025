package org.example.userauthservice_asw_may2025.controllers;

import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthservice_asw_may2025.dtos.LoginRequestDto;
import org.example.userauthservice_asw_may2025.dtos.SignupRequestDto;
import org.example.userauthservice_asw_may2025.dtos.UserDto;
import org.example.userauthservice_asw_may2025.exceptions.PasswordMismatchException;
import org.example.userauthservice_asw_may2025.exceptions.UserAlreadySignedException;
import org.example.userauthservice_asw_may2025.exceptions.UserNotRegisteredException;
import org.example.userauthservice_asw_may2025.models.User;
import org.example.userauthservice_asw_may2025.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
       Pair<User,String> userWithToken =  authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
       UserDto userDto = from(userWithToken.a);
       String token = userWithToken.b;
       MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
       headers.add(HttpHeaders.SET_COOKIE,token);
       return new ResponseEntity<>(userDto,headers,HttpStatus.OK);
    }

    private UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}

