package org.example.userauthservice_asw_may2025.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthservice_asw_may2025.exceptions.AccountSuspendedException;
import org.example.userauthservice_asw_may2025.exceptions.PasswordMismatchException;
import org.example.userauthservice_asw_may2025.exceptions.UserAlreadySignedException;
import org.example.userauthservice_asw_may2025.exceptions.UserNotRegisteredException;
import org.example.userauthservice_asw_may2025.models.State;
import org.example.userauthservice_asw_may2025.models.User;
import org.example.userauthservice_asw_may2025.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signup(String name, String email, String password) {
       Optional<User> userOptional = userRepo.findByEmailEquals(email);
       if(userOptional.isPresent()) {
           throw new UserAlreadySignedException("Please try login directly !!");
       }

       //Brainstorm and add condition for state check

       User user = new User();
       user.setName(name);
       user.setEmail(email);
       user.setPassword(bCryptPasswordEncoder.encode(password));
       return userRepo.save(user);
    }


    public Pair<User,String> login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmailEquals(email);
        if(userOptional.isEmpty()) {
           throw new UserNotRegisteredException("Please try signup first");
        }

        if(!userOptional.get().getState().equals(State.ACTIVE)) {
          throw new AccountSuspendedException("Account is temporarily suspended, Please try after some days");
        }

        String storedPassword = userOptional.get().getPassword();
        //if(!storedPassword.equals(password)) {
        if(!bCryptPasswordEncoder.matches(password,storedPassword))   {
           throw new PasswordMismatchException("Please type correct password");
        }

        //Token Generation

//        String message = "{\n" +
//                "   \"email\": \"anurag@gmail.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"buddy\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"2ndApril2026\"\n" +
//                "}";
//
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userOptional.get().getId());
        Long nowInMillis = System.currentTimeMillis();
        claims.put("iat",nowInMillis);
        claims.put("exp",nowInMillis+100000);
        claims.put("iss","authservice##");

        MacAlgorithm algorithm = Jwts.SIG.HS256;
        SecretKey secretKey = algorithm.key().build();

        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        Pair<User,String> response = new Pair<>(userOptional.get(),token);

        return response;
    }
}

//[k1:v1],[k2:v2],[k3:v3]