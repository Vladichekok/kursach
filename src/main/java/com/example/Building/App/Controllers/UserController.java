package com.example.Building.App.Controllers;

import com.example.Building.App.Model.User;
import com.example.Building.App.Model.UserDTO;
import com.example.Building.App.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }


    @PostMapping
    public User createUser(@RequestBody UserDTO user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
        User updatedUser = userService.updateUser(id,user);
        if (updatedUser != null)
        {return ResponseEntity.ok(updatedUser);}
        else {return ResponseEntity.notFound().build();}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}


