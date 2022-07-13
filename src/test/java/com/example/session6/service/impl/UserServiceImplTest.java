package com.example.session6.service.impl;

import com.example.session6.dto.UserDTO;
import com.example.session6.model.Role;
import com.example.session6.model.User;
import com.example.session6.model.UserDetail;
import com.example.session6.repository.RoleRepository;
import com.example.session6.repository.UserDetailsRepository;
import com.example.session6.repository.UserRepository;
import com.example.session6.service.RoleService;
import com.example.session6.service.UserDetailService;
import com.example.session6.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceImplTest {

    private UserService userService;
    private UserService userServiceSpy;
    private UserRepository userRepository;
    private UserDetailService userDetailService;
    private RoleRepository roleRepository;
    private RoleService roleService;
    private UserDetailsRepository userDetailsRepository;


    @BeforeEach
    void setUp(){
        userRepository = Mockito.mock(UserRepository.class);
        userDetailService = Mockito.mock(UserDetailService.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        roleService = Mockito.mock(RoleService.class);
        userDetailsRepository = Mockito.mock(UserDetailsRepository.class);

        userService = new UserServiceImpl(userRepository, userDetailService, roleRepository, roleService, userDetailsRepository);
        userServiceSpy = Mockito.spy(userService);
    }

    @Test
    void saveReturnsUserDtoWhenUserSaved() {
        UserDTO userDTO = new UserDTO();
        User user = new User();
        Role role = new Role();
        role.setName("ROLE_GUEST");
        userDTO.setUsername("username");
        userDTO.setPassword("pass");

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        Mockito.when(roleRepository.findByName("ROLE_GUEST")).thenReturn(role);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserDTO actualUserDTO = userService.save(userDTO);

        assertEquals(user.getUsername(), actualUserDTO.getUsername());

    }

    @Test
    void updateReturnsUserDtoWhenUserUpdated() {
        UserDTO userDTO = new UserDTO();
        User user = new User();
        Role role = new Role();
        role.setName("ROLE_GUEST");
        userDTO.setUsername("username");
        userDTO.setPassword("pass");
        userDTO.setId(1);

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setId(userDTO.getId());

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(userDetailsRepository.findByUserId(any())).thenReturn(new UserDetail());
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.when(roleRepository.findByName(any())).thenReturn(role);

        UserDTO actualUserDTO = userService.save(userDTO);

        assertEquals(user.getUsername(), actualUserDTO.getUsername());

    }

    @Test
    void updateThrowsRuntimeErrorWhenIdInvalid() {
        UserDTO userDTO = new UserDTO();
        User user = new User();
        Role role = new Role();
        Boolean throwed = null;
        role.setName("ROLE_GUEST");
        userDTO.setUsername("username");
        userDTO.setPassword("pass");
        userDTO.setId(1);

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setId(userDTO.getId());

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        try {
            UserDTO actualUserDTO = userService.save(userDTO);
        }catch (RuntimeException e){
            throwed = true;
        }
        assertTrue(throwed);

    }

    @Test
    void findByIdReturnsUserDtoWhenUserFound() {

        User user = new User();

        user.setId(10);
        user.setUsername("username");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));

        UserDTO actualUserDTO = userService.findById(user.getId());

        assertEquals(user.getUsername(), actualUserDTO.getUsername());
    }

    @Test
    void findByIdReturnsNullWhenUserNotFound() {

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        UserDTO actualUserDTO = userService.findById(2);

        assertNull(actualUserDTO);
    }
}