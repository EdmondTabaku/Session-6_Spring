package com.example.session6.controller;

import com.example.session6.dto.UserDTO;
import com.example.session6.model.User;
import com.example.session6.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Finding all the users
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    // Finding a user by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public UserDTO findById(@PathVariable(name = "id") int id){
        UserDTO userDTO = userService.findById(id);
        if (userDTO != null){
            return userDTO;
        }
        else {
            throw  new ResponseStatusException(HttpStatus.resolve(404), "User not found");
        }
    }

    // Finding all users in ascending order by a specified field
    @GetMapping("/asc/{field}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public List<UserDTO> findAllSortedASC(@PathVariable(name = "field") String field){
        return userService.findAllSortedASC(field);
    }

    // Finding all users in descending order by a specified field
    @GetMapping("/desc/{field}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public List<UserDTO> findAllSortedDESC(@PathVariable(name = "field") String field){
        return userService.findAllSortedDESC(field);
    }

    // Finding users with pagination by specifying the page number and the page size
    @GetMapping
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public List<UserDTO> findAllWithPagination(@RequestParam(name = "pageNumber") int pageNumber,
                                               @RequestParam(name = "pageSize") int pageSize){
        return userService.findAllWithPagination(pageNumber, pageSize);
    }

    // Saving a new user
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDTO save(@RequestBody UserDTO user){
        return userService.save(user);
    }

    // Updating a user
    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDTO put(@RequestBody UserDTO user){
        return userService.save(user);
    }

    // Adding a role to a user
    @PostMapping("/role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Boolean addRoleToUser(@RequestParam(name = "username") String username,
                                 @RequestParam(name = "role") String role){
        return userService.addRoleToUser(username, role);
    }

    // Deleting a user by it's id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Boolean delete(@PathVariable(name = "id") int id){
        try {
            userService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
