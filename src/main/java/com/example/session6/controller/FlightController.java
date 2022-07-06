package com.example.session6.controller;

import com.example.session6.dto.FlightDTO;
import com.example.session6.model.Flight;
import com.example.session6.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/flights")
public class FlightController {
    
    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Finding all the flights
    @GetMapping
    public List<FlightDTO> findAll(){
        return flightService.findAll();
    }

    // Finding a flight by id
    @GetMapping("/{id}")
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
    public FlightDTO save(@RequestBody Flight flight){
        return flightService.save(flight);
    }

    // Updating a flight
    @PutMapping
    public FlightDTO put(@RequestBody Flight flight){
        return flightService.save(flight);
    }

    // Deleting a flight by it's id
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable(name = "id") int id){
        try {
            flightService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
