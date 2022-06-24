package com.example.session6.service;

import com.example.session6.model.Booking;

import java.util.List;

public interface BookingService {
    public Booking save(Booking booking);

    public List<Booking> findAll();

    public Booking findById(int id);

    public void delete(int id);
}
