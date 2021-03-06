package com.example.session6.service.impl;

import com.example.session6.dto.BookingDTO;
import com.example.session6.dto.FlightDTO;
import com.example.session6.model.Booking;
import com.example.session6.model.Flight;
import com.example.session6.model.User;
import com.example.session6.repository.BookingRepository;
import com.example.session6.repository.FlightRepository;
import com.example.session6.repository.UserRepository;
import com.example.session6.service.BookingService;
import com.example.session6.service.FlightService;
import com.example.session6.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
    private final BookingRepository bookingRepository;
    private final FlightService flightService;
    private final UserService userService;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightService flightService, UserService userService, FlightRepository flightRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
        this.userService = userService;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    // Saving a booking
    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        Booking booking;
        User user;
        Set<Flight> flights = new HashSet<>();

        if (bookingDTO.getId() != null) {
            Optional<Booking> bookingOptional = bookingRepository.findById(bookingDTO.getId());
            if (bookingOptional.isPresent()) {
                booking = bookingOptional.get();

            } else {
                logger.error("Booking id is invalid");
                throw new RuntimeException("Id invalid");
            }
        } else {
            booking = new Booking();
        }
        for (FlightDTO flightDTO : bookingDTO.getFlights()) {

            Optional<Flight> flightOptional = flightRepository.findById(flightDTO.getId());
            if (flightOptional.isPresent()) {
                Flight flight = flightOptional.get();
                flights.add(flight);
            } else {

                logger.error("Flight id is invalid");
                throw new RuntimeException("Flight Id invalid");
            }

        }

        Optional<User> userOptional = userRepository.findById(bookingDTO.getUser().getId());
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            logger.error("User id is invalid");
            throw new RuntimeException("User Id invalid");
        }

        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setStatus(bookingDTO.getStatus());
        booking.setFlights(flights);
        booking.setUser(user);

        logger.info("Saved booking with booking date " + booking.getBookingDate());

        return convertToDTO(bookingRepository.save(booking));
    }

    // Finding all the bookings
    @Override
    public List<BookingDTO> findAll() {
        List<BookingDTO> bookingDTOList = bookingRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

        logger.info("Found all the bookings");
        return bookingDTOList;
    }

    // Finding a booking by id
    @Override
    public BookingDTO findById(int id) {

        Booking booking;
        Optional<Booking> bookingOptional = bookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            booking = bookingOptional.get();
            logger.info("Found booking with id: " + id);
            return convertToDTO(booking);
        } else {
            logger.warn("Booking with id: " + id + " not found");
            return null;
        }

    }

    // Deleting a booking
    @Override
    public void delete(int id) {
        bookingRepository.deleteById(id);
        logger.info("Deleted booking with id: " + id);
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
