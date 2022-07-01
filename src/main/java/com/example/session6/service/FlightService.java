package com.example.session6.service;

import com.example.session6.dto.FlightDTO;
import com.example.session6.dto.UserDTO;
import com.example.session6.model.Flight;
import com.example.session6.model.User;

import java.util.List;

public interface FlightService {
    public FlightDTO save(Flight flight);

    public List<FlightDTO> findAll();

    public FlightDTO findById(int id);

    public void delete(int id);

    public FlightDTO convertToDTO(Flight flight);
}
