package com.example.session6.repository;

import com.example.session6.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    public Flight save(Flight flight);

    public List<Flight> findAll();

    public Flight findById(int id);

    public void deleteById(int id);
}
