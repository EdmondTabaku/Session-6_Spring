package com.example.session6.repository.User;

import com.example.session6.model.User;

import java.util.List;

public interface UserRepository {
    public User save(User user);

    public List<User> findAll();

    public User findById(int id);

    public void delete(int id);
}
