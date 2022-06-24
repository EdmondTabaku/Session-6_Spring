package com.example.session6.service;

import com.example.session6.model.User;

import java.util.List;

public interface UserService {
    public User save(User user);

    public List<User> findAll();

    public User findById(int id);

    public void delete(int id);
}
