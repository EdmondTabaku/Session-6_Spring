package com.example.session6.service;

import com.example.session6.dto.UserDTO;
import com.example.session6.model.User;
import com.example.session6.model.UserDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public UserDTO save(UserDTO userDTO);

    public List<UserDTO> findAll();

    public List<UserDTO> findAllSortedASC(String field);

    public List<UserDTO> findAllSortedDESC(String field);

    public List<UserDTO> findAllWithPagination(int next, int pagesize);

    public UserDTO findById(int id);

    public Boolean addRoleToUser(String username, String role);

    public void delete(int id);

    public UserDTO convertToDTO(User user);
}
