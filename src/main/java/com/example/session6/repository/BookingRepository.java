package com.example.session6.repository;

import com.example.session6.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    public Booking save(Booking booking);

    public List<Booking> findAll();

    public Booking findById(int id);

    public void deleteById(int id);
}
