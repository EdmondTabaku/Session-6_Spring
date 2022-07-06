package com.example.session6.service.impl;

import com.example.session6.model.User;
import com.example.session6.model.UserDetails;

import com.example.session6.repository.UserDetailsRepository;
import com.example.session6.service.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    // Saving user details
    @Override
    public UserDetails save(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    // Finding all the user details
    @Override
    public List<UserDetails> findAll() {
        return userDetailsRepository.findAll();
    }

    // Finding a user detail by id
    @Override
    public UserDetails findById(int id) {

        UserDetails userDetails = new UserDetails();
        Optional<UserDetails> userDetailsOptional = userDetailsRepository.findById(id);

        if (userDetailsOptional.isPresent()){
            userDetails = userDetailsOptional.get();
        }

        return userDetails;
    }

    // Deleting a user datail
    @Override
    public void delete(int id) {
        userDetailsRepository.deleteById(id);
    }
}
