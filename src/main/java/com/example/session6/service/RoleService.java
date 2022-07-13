package com.example.session6.service;

import com.example.session6.dto.RoleDTO;
import com.example.session6.model.Role;

import java.util.List;

public interface RoleService {

    public RoleDTO save(RoleDTO roleDTO);

    public List<RoleDTO> findAll();

    public RoleDTO findById(int id);

    public void delete(int id);

    public RoleDTO convertToDTO(Role role);
}
