package com.example.session6.service.impl;

import com.example.session6.dto.UserDTO;
import com.example.session6.model.User;
import com.example.session6.repository.UserRepository;
import com.example.session6.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Saving user
    @Override
    public UserDTO save(User user) {
        UserDTO userDTO = convertToDTO(userRepository.save(user));
        return userDTO;
    }

    // Finding all the users
    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOList = userRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        return userDTOList;
    }

    // Finding all users sorted in ascending order
    @Override
    public List<UserDTO> findAllSortedASC(String field) {
        List<UserDTO> userDTOList = userRepository.findAll(Sort.by(Sort.Direction.ASC, field))
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        return userDTOList;
    }

    // Finding all users sorted in descending order
    @Override
    public List<UserDTO> findAllSortedDESC(String field) {
        List<UserDTO> userDTOList = userRepository.findAll(Sort.by(Sort.Direction.DESC, field))
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        return userDTOList;
    }

    // Finding users with pagination by specifying the page number and the page size
    @Override
    public List<UserDTO> findAllWithPagination(int next, int pagesize) {
        List<UserDTO> userDTOList = userRepository.findAll(PageRequest.of(next, pagesize))
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        return userDTOList;
    }

    // Finding a user by id
    @Override
    public UserDTO findById(int id) {

        User user = new User();
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            user = userOptional.get();
            return convertToDTO(user);
        }
        else {
            return null;
        }


    }

    // Deleting a user
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    // Converting from User to UserDTO
    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        if (user.getUserDetails() != null){
            userDTO.setFirstName(user.getUserDetails().getFirstName());
            userDTO.setLastName(user.getUserDetails().getLastName());
            userDTO.setPhone(user.getUserDetails().getPhoneNumber());
            userDTO.setEmail(user.getUserDetails().getEmail());
        }

        return userDTO;
    }
}
