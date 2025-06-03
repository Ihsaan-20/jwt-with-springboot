package com.example.simple_jwt_login.repo;

import com.example.simple_jwt_login.dto.UserView;
import com.example.simple_jwt_login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u.username AS username, u.role AS role FROM User u")
    List<UserView> findAllProjectedUsers();
}
