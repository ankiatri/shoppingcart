package com.ethoca.elimininator.shoppingcart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ethoca.elimininator.shoppingcart.entity.User;
import com.ethoca.elimininator.shoppingcart.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        User userResponse = userService.saveUser(user);
        return new ResponseEntity(userResponse, HttpStatus.CREATED);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUserName(username);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(user, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable("userId") Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return new ResponseEntity(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(user, HttpStatus.NOT_FOUND);
        }
    }

}
