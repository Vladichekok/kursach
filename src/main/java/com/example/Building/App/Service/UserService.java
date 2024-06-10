package com.example.Building.App.Service;

import com.example.Building.App.Model.User;
import com.example.Building.App.Model.UserDTO;
import com.example.Building.App.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserNameContainingIgnoreCase(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User createUser(UserDTO userDTO){
        var user = new User(0L,userDTO.userName(),userDTO.password());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUserData) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(updatedUserData.getUserName());
                    user.setPassword(updatedUserData.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
}
