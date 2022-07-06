package com.example.session6.controller;

import com.example.session6.dto.UserDTO;
import com.example.session6.model.User;
import com.example.session6.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Finding all the users
    @GetMapping("/all")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    // Finding a user by id
    @GetMapping("/{id}")
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
    public List<UserDTO> findAllSortedASC(@PathVariable(name = "field") String field){
        return userService.findAllSortedASC(field);
    }

    // Finding all users in descending order by a specified field
    @GetMapping("/desc/{field}")
    public List<UserDTO> findAllSortedDESC(@PathVariable(name = "field") String field){
        return userService.findAllSortedDESC(field);
    }

    // Finding users with pagination by specifying the page number and the page size
    @GetMapping
    public List<UserDTO> findAllWithPagination(@RequestParam(name = "pageNumber") int pageNumber,
                                               @RequestParam(name = "pageSize") int pageSize){
        return userService.findAllWithPagination(pageNumber, pageSize);
    }

    // Saving a new user
    @PostMapping
    public UserDTO save(@RequestBody User user){
        return userService.save(user);
    }

    // Updating a user
    @PutMapping
    public UserDTO put(@RequestBody User user){
        return userService.save(user);
    }

    // Deleting a user by it's id
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable(name = "id") int id){
        try {
            userService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
