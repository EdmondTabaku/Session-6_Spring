package com.example.session6.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactory {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("PersistenceUnit");
    private EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
