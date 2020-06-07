package com.ethoca.elimininator.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import com.ethoca.elimininator.shoppingcart.entity.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User getUserByUserName(String username);

    User saveUser(User user);
}
