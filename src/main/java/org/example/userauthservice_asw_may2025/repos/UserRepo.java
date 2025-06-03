package org.example.userauthservice_asw_may2025.repos;

import org.example.userauthservice_asw_may2025.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmailEquals(String email);

    User save(User user);
}
