package com.example.session6.service.impl;

import com.example.session6.dto.UserDTO;
import com.example.session6.model.User;
import com.example.session6.repository.UserRepository;
import com.example.session6.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(User user) {
        UserDTO userDTO = convertToDTO(userRepository.save(user));
        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOList = userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public UserDTO findById(int id) {
        UserDTO userDTO = convertToDTO(userRepository.findById(id));
        return userDTO;
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getUserDetails().getFirstName());
        userDTO.setLastName(user.getUserDetails().getLastName());
        userDTO.setPhone(user.getUserDetails().getPhoneNumber());
        userDTO.setEmail(user.getUserDetails().getEmail());
        userDTO.setRole(user.getRole());


        return userDTO;
    }
}
