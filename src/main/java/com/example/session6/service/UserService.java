package com.example.session6.service;

import com.example.session6.dto.UserDTO;
import com.example.session6.model.User;

import java.util.List;

public interface UserService {
    public UserDTO save(User user);

    public List<UserDTO> findAll();

    public UserDTO findById(int id);

    public void delete(int id);

    public UserDTO convertToDTO(User user);
}
