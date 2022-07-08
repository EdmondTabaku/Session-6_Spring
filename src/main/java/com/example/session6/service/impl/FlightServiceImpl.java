package com.example.session6.service.impl;

import com.example.session6.dto.FlightDTO;
import com.example.session6.model.Flight;
import com.example.session6.model.User;
import com.example.session6.repository.FlightRepository;
import com.example.session6.service.BookingService;
import com.example.session6.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Saving a flight
    @Override
    public FlightDTO save(Flight flight) {
        return convertToDTO(flightRepository.save(flight));
    }

    // Finding all the flights
    @Override
    public List<FlightDTO> findAll() {
        List<FlightDTO> flightDTOList = flightRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return flightDTOList;
    }

    // Finding a flight by id
    @Override
    public FlightDTO findById(int id) {
        Flight flight = new Flight();
        Optional<Flight> flightOptional = flightRepository.findById(id);

        if (flightOptional.isPresent()){
            flight = flightOptional.get();
        }

        return convertToDTO(flight);
    }

    // Deleting a flight
    @Override
    public void delete(int id) {
        flightRepository.deleteById(id);
    }

    // Converting from Flight to FlightDTO
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
