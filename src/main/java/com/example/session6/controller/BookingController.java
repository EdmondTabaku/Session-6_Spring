package com.example.session6.controller;

import com.example.session6.dto.BookingDTO;
import com.example.session6.model.Booking;
import com.example.session6.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/bookings")
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Finding all the bookings
    @GetMapping
    public List<BookingDTO> findAll(){
        return bookingService.findAll();
    }

    // Finding a booking by id
    @GetMapping("/{id}")
    public BookingDTO findById(@PathVariable(name = "id") int id){
        BookingDTO bookingDTO = bookingService.findById(id);
        if (bookingDTO != null){
            return bookingDTO;
        }
        else {
            throw  new ResponseStatusException(HttpStatus.resolve(404), "Booking not found");
        }
    }

    // Saving a new booking
    @PostMapping
    public BookingDTO save(@RequestBody Booking booking){
        return bookingService.save(booking);
    }

    // Updating a booking
    @PutMapping
    public BookingDTO put(@RequestBody Booking booking){
        return bookingService.save(booking);
    }

    // Deleting a booking by it's id
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable(name = "id") int id){
        try {
            bookingService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
