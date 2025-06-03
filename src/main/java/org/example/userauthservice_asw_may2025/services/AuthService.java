package org.example.userauthservice_asw_may2025.services;

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

import java.util.Optional;

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


    public User login(String email, String password) {
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

        return userOptional.get();
    }
}
