package com.example.session6.controller;

import com.example.session6.dto.FlightDTO;
import com.example.session6.model.Flight;
import com.example.session6.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/flights")
public class FlightController {
    
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Finding all the flights
    @GetMapping
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public List<FlightDTO> findAll(){
        return flightService.findAll();
    }

    // Finding a flight by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public FlightDTO findById(@PathVariable(name = "id") int id){
        FlightDTO flightDTO = flightService.findById(id);
        if (flightDTO != null){
            return flightDTO;
        }
        else {
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Flight not found");
        }
    }
    
    // Saving a new flight
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public FlightDTO save(@RequestBody FlightDTO flight){
        return flightService.save(flight);
    }

    // Updating a flight
    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public FlightDTO put(@RequestBody FlightDTO flight){
        return flightService.save(flight);
    }

    // Deleting a flight by it's id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Boolean delete(@PathVariable(name = "id") int id){
        try {
            flightService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
