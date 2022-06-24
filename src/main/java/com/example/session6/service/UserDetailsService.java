package com.example.session6.service;

import com.example.session6.model.UserDetails;

import java.util.List;

public interface UserDetailsService {
    public UserDetails save(UserDetails userDetails);

    public List<UserDetails> findAll();

    public UserDetails findById(int id);

    public void delete(int id);
}
