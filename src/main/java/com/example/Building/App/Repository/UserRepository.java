package com.example.Building.App.Repository;

import com.example.Building.App.Model.Project;
import com.example.Building.App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserNameContainingIgnoreCase(String userName);
}
