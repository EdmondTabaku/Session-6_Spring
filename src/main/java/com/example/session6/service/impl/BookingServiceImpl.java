package com.example.session6.service.impl;

import com.example.session6.dto.BookingDTO;
import com.example.session6.model.Booking;
import com.example.session6.model.User;
import com.example.session6.repository.BookingRepository;
import com.example.session6.service.BookingService;
import com.example.session6.service.FlightService;
import com.example.session6.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final FlightService flightService;
    private final UserService userService;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightService flightService, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
        this.userService = userService;
    }

    // Saving a booking
    @Override
    public BookingDTO save(Booking booking) {
        return convertToDTO(bookingRepository.save(booking));
    }

    // Finding all the bookings
    @Override
    public List<BookingDTO> findAll() {
        List<BookingDTO> bookingDTOList = bookingRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

        return bookingDTOList;
    }

    // Finding a booking by id
    @Override
    public BookingDTO findById(int id) {

        Booking booking;
        Optional<Booking> bookingOptional = bookingRepository.findById(id);

        if (bookingOptional.isPresent()){
            booking = bookingOptional.get();
            return convertToDTO(booking);
        }
        else {
            return null;
        }

    }

    // Deleting a booking
    @Override
    public void delete(int id) {
        bookingRepository.deleteById(id);
    }

    // Converting from Booking to BookingDTO
    @Override
    public BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setFlights(booking.getFlights().stream().map(this.flightService::convertToDTO).collect(Collectors.toSet()));
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setUser(userService.convertToDTO(booking.getUser()));

        return bookingDTO;
    }
}
