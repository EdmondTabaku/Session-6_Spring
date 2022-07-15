package com.example.session6.service.impl;

import com.example.session6.dto.RoleDTO;
import com.example.session6.dto.UserDTO;
import com.example.session6.model.Role;
import com.example.session6.model.User;
import com.example.session6.repository.RoleRepository;
import com.example.session6.service.RoleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

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
        logger.info("Saved user with name " + role.getName());
        return convertToDTO(roleRepository.save(role));
    }

    @Override
    public List<RoleDTO> findAll() {
        List<RoleDTO> roleDTOList = roleRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        logger.info("Found all the roles");
        return roleDTOList;
    }

    @Override
    public RoleDTO findById(int id) {
        Role role;
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()){
            role = roleOptional.get();
            logger.info("Found role with id: " + id);
            return convertToDTO(role);
        }
        else {
            logger.warn("Role with id: " + id + " not found");
            return null;
        }
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
        logger.info("Deleted user with id: " + id);
    }

    @Override
    public RoleDTO convertToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());

        return roleDTO;
    }
}
