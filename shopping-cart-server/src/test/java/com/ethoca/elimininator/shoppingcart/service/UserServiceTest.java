package com.ethoca.elimininator.shoppingcart.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ethoca.elimininator.shoppingcart.entity.User;
import com.ethoca.elimininator.shoppingcart.repository.UserRepository;
import com.ethoca.elimininator.shoppingcart.service.impl.UserServiceImpl;

/**
 * Test {@link UserService}.
 */
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    private User mockUser;
    private List<User> mockUsers = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testUserName");
        mockUser.setPassword("testPassword");
        mockUser.setFirstName("firstName");
        mockUser.setLastName("lastName");
        mockUser.setEmail("email");
        mockUsers.add(mockUser);
    }

    @Test
    public void verify_getAllUsers() {
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void verify_saveUser() {
        userService.saveUser(mockUser);
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    public void verify_getUserById() {
        userService.getUserById(1L);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void verify_getUserByUsername() {
        userService.getUserByUserName("testUserName");
        verify(userRepository, times(1)).findByUsername("testUserName");
    }
}
