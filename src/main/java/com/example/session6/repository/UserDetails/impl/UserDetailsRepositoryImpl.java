package com.example.session6.repository.UserDetails.impl;

import com.example.session6.model.UserDetails;
import com.example.session6.repository.UserDetails.UserDetailsRepository;
import com.example.session6.util.EntityFactory;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserDetailsRepositoryImpl implements UserDetailsRepository {
    EntityFactory entityFactory = new EntityFactory();


    @Override
    public UserDetails save(UserDetails userDetails) {
        EntityManager em = entityFactory.getEntityManager();
        if (userDetails != null){
            em.getTransaction().begin();
            em.merge(userDetails);
            em.getTransaction().commit();
        }
        else {
            em.getTransaction().begin();
            em.persist(userDetails);
            em.getTransaction().commit();
        }
        em.close();
        return userDetails;
    }

    @Override
    public List<UserDetails> findAll() {
        EntityManager em = entityFactory.getEntityManager();
        Query query = em.createQuery("SELECT u FROM UserDetails u");
        List<UserDetails> userDetailsList = query.getResultList();
        em.close();
        return userDetailsList;
    }

    @Override
    public UserDetails findById(int id) {
        EntityManager em = entityFactory.getEntityManager();
        UserDetails userDetails = em.find(UserDetails.class, id);
        return userDetails;
    }

    @Override
    public void delete(int id) {
        UserDetails userDetailsToDelete = findById(id);
        EntityManager em = entityFactory.getEntityManager();
        em.getTransaction().begin();
        em.remove(userDetailsToDelete);
        em.getTransaction().commit();
        em.close();
        System.out.println("Deleted user details with id: " + id);
    }
}
