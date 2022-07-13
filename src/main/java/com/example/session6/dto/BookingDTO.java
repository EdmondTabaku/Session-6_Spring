package com.example.session6.dto;


import java.sql.Date;
import java.util.Set;

public class BookingDTO {

    private Integer id;
    private String status;
    private Date bookingDate;
    private UserDTO user;
    private Set<FlightDTO> flights;


    ///////////////////////////////////////////////////////////////////////
    ///////////////////////// GETTERS AND SETTERS /////////////////////////
    ///////////////////////////////////////////////////////////////////////


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Set<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightDTO> flights) {
        this.flights = flights;
    }
}
