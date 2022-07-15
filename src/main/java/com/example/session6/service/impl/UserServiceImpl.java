package com.example.session6.service.impl;

import com.example.session6.dto.RoleDTO;
import com.example.session6.dto.UserDTO;
import com.example.session6.model.Role;
import com.example.session6.model.User;
import com.example.session6.model.UserDetail;
import com.example.session6.repository.RoleRepository;
import com.example.session6.repository.UserDetailsRepository;
import com.example.session6.repository.UserRepository;
import com.example.session6.service.RoleService;
import com.example.session6.service.UserDetailService;
import com.example.session6.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserDetailService userDetailService;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final UserDetailsRepository userDetailsRepository;



    public UserServiceImpl(UserRepository userRepository, UserDetailService userDetailService, RoleRepository roleRepository, RoleService roleService, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userDetailService = userDetailService;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.userDetailsRepository = userDetailsRepository;
    }


    // Saving user
    @Override
    public UserDTO save(UserDTO userDTO) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user;
        UserDetail userDetail;
        Collection<Role> roles = new ArrayList<>();

        if (userDTO.getId() != null){
            Optional<User> userOptional = userRepository.findById(userDTO.getId());
            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                logger.error("User id is invalid");
                throw new RuntimeException("Id invalid");
            }
            userDetail = userDetailsRepository.findByUserId(user.getId());
            if (userDTO.getRoles() != null){
                for (RoleDTO role : userDTO.getRoles()) {
                    Role role1 = roleRepository.findByName(role.getName());
                    roles.add(role1);
                }
            }

        }else {
            user = new User();
            userDetail = new UserDetail();
            Role guest = roleRepository.findByName("ROLE_GUEST");
            roles.add(guest);
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roles);

        if (userDTO.getUserDetail() != null){
            userDetail.setFirstName(userDTO.getUserDetail().getFirstName());
            userDetail.setLastName(userDTO.getUserDetail().getLastName());
            userDetail.setEmail(userDTO.getUserDetail().getEmail());
            userDetail.setPhoneNumber(userDTO.getUserDetail().getPhone());
            userDetail.setUser(user);
        }

        user.setUserDetails(userDetail);

        logger.info("Saved user with username " + user.getUsername());

        return convertToDTO(userRepository.save(user));
    }

    // Finding all the users
    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOList = userRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        logger.info("Found all the users");
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

        User user;
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            user = userOptional.get();
            logger.info("Found user with id: " + id);
            return convertToDTO(user);
        } else {
            logger.warn("User with id: " + id + " not found");
            return null;
        }

    }

    // Adding a role to a user
    @Override
    public Boolean addRoleToUser(String username, String roleName) {
        try {
            User user = userRepository.findByUsername(username);
            Role role = roleRepository.findByName(roleName);
            if (role != null && user != null) {
                user.getRoles().add(role);
                userRepository.save(user);
                logger.info("Added role " + role.getName() + " to user with username " + user.getUsername());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Unable to add role to user. Error: " + e);
            return false;
        }
    }

    // Deleting a user
    @Override
    public void delete(int id) {

        userRepository.deleteById(id);
        logger.info("Deleted user with id: " + id);
    }

    // Converting from User to UserDTO
    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoles().stream().map(roleService::convertToDTO).collect(Collectors.toList()));

        if (user.getUserDetails() != null) {
            userDTO.setUserDetail(userDetailService.convertToDTO(user.getUserDetails()));
        } else {
            userDTO.setUserDetail(null);
        }

        return userDTO;
    }


}
