package com.example.session6.repository;

import com.example.session6.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    public UserDetails save(UserDetails userDetails);

    public List<UserDetails> findAll();

    public UserDetails findById(int id);

    public void deleteById(int id);
}
