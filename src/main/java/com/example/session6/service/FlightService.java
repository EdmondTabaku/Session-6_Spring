package com.example.session6.service;

import com.example.session6.model.Flight;

import java.util.List;

public interface FlightService {
    public Flight save(Flight flight);

    public List<Flight> findAll();

    public Flight findById(int id);

    public void delete(int id);
}
