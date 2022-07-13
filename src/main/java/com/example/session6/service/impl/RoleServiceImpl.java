package com.example.session6.service.impl;

import com.example.session6.dto.RoleDTO;
import com.example.session6.dto.UserDTO;
import com.example.session6.model.Role;
import com.example.session6.model.User;
import com.example.session6.repository.RoleRepository;
import com.example.session6.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleRepository.findByName(roleDTO.getName());
        if (role == null){
            role = new Role();
        }
        role.setName(roleDTO.getName());
        return convertToDTO(roleRepository.save(role));
    }

    @Override
    public List<RoleDTO> findAll() {
        List<RoleDTO> roleDTOList = roleRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        return roleDTOList;
    }

    @Override
    public RoleDTO findById(int id) {
        Role role;
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()){
            role = roleOptional.get();
            return convertToDTO(role);
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDTO convertToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());

        return roleDTO;
    }
}
