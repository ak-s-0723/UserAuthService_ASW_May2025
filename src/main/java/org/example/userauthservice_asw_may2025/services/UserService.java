package org.example.userauthservice_asw_may2025.services;

import org.example.userauthservice_asw_may2025.models.User;
import org.example.userauthservice_asw_may2025.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserDetail(Long id) {
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty()) {
            System.out.println("NO USER FOUND");
            return null;
        }

        System.out.println(userOptional.get().getEmail());
        return userOptional.get();
    }


}
