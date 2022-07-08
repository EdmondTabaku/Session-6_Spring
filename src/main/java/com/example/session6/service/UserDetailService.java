package com.example.session6.service;

import com.example.session6.dto.UserDetailDTO;
import com.example.session6.model.UserDetail;

import java.util.List;

public interface UserDetailService {
    public UserDetailDTO save(UserDetail userDetail);

    public List<UserDetailDTO> findAll();

    public UserDetailDTO findById(int id);

    public void delete(int id);

    public UserDetailDTO convertToDTO(UserDetail userDetail);
}
