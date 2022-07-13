package com.example.session6.controller;

import com.example.session6.dto.FlightDTO;
import com.example.session6.dto.RoleDTO;
import com.example.session6.model.Flight;
import com.example.session6.model.Role;
import com.example.session6.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> findAll(){
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable(name = "id") int id){
        RoleDTO roleDTO = roleService.findById(id);
        if (roleDTO != null){
            return roleDTO;
        }
        else {
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Role not found");
        }
    }

    @PostMapping
    public RoleDTO save(@RequestBody RoleDTO role){
        return roleService.save(role);
    }

    // Updating a role
    @PutMapping
    public RoleDTO put(@RequestBody RoleDTO role){
        return roleService.save(role);
    }

    // Deleting a role by it's id
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable(name = "id") int id){
        try {
            roleService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
