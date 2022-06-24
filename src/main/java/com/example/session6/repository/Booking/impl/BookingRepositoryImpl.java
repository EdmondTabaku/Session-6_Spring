package com.example.session6.repository.Booking.impl;

import com.example.session6.model.Booking;
import com.example.session6.repository.Booking.BookingRepository;
import com.example.session6.util.EntityFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class BookingRepositoryImpl implements BookingRepository {
    EntityFactory entityFactory = new EntityFactory();

    @Override
    public Booking save(Booking booking) {
        EntityManager em = entityFactory.getEntityManager();
        if (booking != null){
            em.getTransaction().begin();
            em.merge(booking);
            em.getTransaction().commit();
        }
        else {
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
        }
        em.close();
        return booking;
    }

    @Override
    public List<Booking> findAll() {
        EntityManager em = entityFactory.getEntityManager();
        Query query = em.createQuery("SELECT b FROM Booking b");
        List<Booking> bookings = query.getResultList();
        em.close();
        return bookings;
    }

    @Override
    public Booking findById(int id) {
        EntityManager em = entityFactory.getEntityManager();
        Booking booking = em.find(Booking.class, id);
        return booking;
    }

    @Override
    public void delete(int id) {
        Booking bookingToDelete= findById(id);
        EntityManager em = entityFactory.getEntityManager();
        em.getTransaction().begin();
        em.remove(bookingToDelete);
        em.getTransaction().commit();
        em.close();
        System.out.println("Deleted booking with id: " + id);
    }
}
