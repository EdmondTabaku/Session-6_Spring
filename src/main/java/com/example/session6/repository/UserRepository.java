package com.example.session6.repository;

import com.example.session6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User save(User user);

    public List<User> findAll();

    public User findById(int id);

    public void deleteById(int id);
}
