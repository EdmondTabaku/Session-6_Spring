package com.example.session6.service.impl;

import com.example.session6.model.Flight;
import com.example.session6.repository.Flight.FlightRepository;
import com.example.session6.repository.Flight.impl.FlightRepositoryImpl;
import com.example.session6.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    FlightRepositoryImpl flightRepository;

    public FlightServiceImpl(FlightRepositoryImpl flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(int id) {
        return flightRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        flightRepository.delete(id);
    }
}
