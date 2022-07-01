package com.example.session6.service.impl;

import com.example.session6.dto.FlightDTO;
import com.example.session6.model.Flight;
import com.example.session6.repository.FlightRepository;
import com.example.session6.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDTO save(Flight flight) {
        return convertToDTO(flightRepository.save(flight));
    }

    @Override
    public List<FlightDTO> findAll() {
        List<FlightDTO> flightDTOList = flightRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return flightDTOList;
    }

    @Override
    public FlightDTO findById(int id) {
        return convertToDTO(flightRepository.findById(id));
    }

    @Override
    public void delete(int id) {
        flightRepository.deleteById(id);
    }

    @Override
    public FlightDTO convertToDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setAirline(flight.getAirline());
        flightDTO.setArrivalDate(flight.getArrivalDate());
        flightDTO.setStatus(flight.getStatus());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setOrigin(flight.getOrigin());
        flightDTO.setDepartureDate(flight.getDepartureDate());

        return flightDTO;
    }
}
