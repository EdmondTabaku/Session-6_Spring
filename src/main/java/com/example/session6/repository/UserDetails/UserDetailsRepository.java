package com.example.session6.repository.UserDetails;

import com.example.session6.model.UserDetails;

import java.util.List;

public interface UserDetailsRepository {
    public UserDetails save(UserDetails userDetails);

    public List<UserDetails> findAll();

    public UserDetails findById(int id);

    public void delete(int id);
}
