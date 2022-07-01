package com.example.session6.service;

import com.example.session6.dto.BookingDTO;
import com.example.session6.dto.UserDTO;
import com.example.session6.model.Booking;
import com.example.session6.model.User;

import java.util.List;

public interface BookingService {
    public BookingDTO save(Booking booking);

    public List<BookingDTO> findAll();

    public BookingDTO findById(int id);

    public void delete(int id);

    public BookingDTO convertToDTO(Booking booking);
}
