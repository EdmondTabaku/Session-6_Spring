package com.example.session6.repository;

import com.example.session6.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetail, Integer> {
    public UserDetail findByUserId(Integer id);
}
