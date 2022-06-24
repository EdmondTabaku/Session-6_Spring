package com.example.session6.service.impl;

import com.example.session6.model.UserDetails;
import com.example.session6.repository.UserDetails.UserDetailsRepository;
import com.example.session6.repository.UserDetails.impl.UserDetailsRepositoryImpl;
import com.example.session6.service.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserDetailsRepositoryImpl userDetailsRepository;

    public UserDetailsServiceImpl(UserDetailsRepositoryImpl userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails save(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    @Override
    public List<UserDetails> findAll() {
        return userDetailsRepository.findAll();
    }

    @Override
    public UserDetails findById(int id) {
        return userDetailsRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        userDetailsRepository.delete(id);
    }
}
