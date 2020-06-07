package com.ethoca.elimininator.shoppingcart.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ethoca.elimininator.shoppingcart.entity.User;
import com.ethoca.elimininator.shoppingcart.service.UserService;

/**
 * Test {@link UserController}.
 */
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    private User mockUser;
    private List<User> mockUsers = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Authentication auth = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContextHolder.getContext().setAuthentication(auth);
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
    public void getAllUsers_thenReturnStatusOk() throws Exception {
        when(userService.getAllUsers()).thenReturn(mockUsers);
        ResponseEntity responseEntity = userController.getAllUsers();
        List<User> response = (List<User>) responseEntity.getBody();
        assertThat(response.size(), equalTo(1));
        assertThat(response.get(0).getId(), equalTo(mockUser.getId()));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

    @Test
    public void createUser_withUniqueUsername_thenReturnResponseCreated() throws Exception {
        when(userService.saveUser(any())).thenReturn(mockUser);
        ResponseEntity<User> responseEntity = userController.saveUser(mockUser);
        User response = responseEntity.getBody();
        assertThat(response.getId(), equalTo(mockUser.getId()));
        assertThat(HttpStatus.CREATED.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

    @Test
    public void getUserById_withValid_thenReturnStatusOk() throws Exception {
        when(userService.getUserById(any())).thenReturn(Optional.of(mockUser));
        ResponseEntity<User> responseEntity = userController.getUserById(1L);
        User response = responseEntity.getBody();
        assertThat(response.getId(), equalTo(mockUser.getId()));
        assertThat(HttpStatus.OK.value(), equalTo(responseEntity.getStatusCodeValue()));
    }

}
