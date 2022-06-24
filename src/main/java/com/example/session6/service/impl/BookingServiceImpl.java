package com.example.session6.service.impl;

import com.example.session6.model.Booking;
import com.example.session6.repository.Booking.BookingRepository;
import com.example.session6.repository.Booking.impl.BookingRepositoryImpl;
import com.example.session6.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    BookingRepositoryImpl bookingRepository;

    public BookingServiceImpl(BookingRepositoryImpl bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        bookingRepository.delete(id);
    }
}
