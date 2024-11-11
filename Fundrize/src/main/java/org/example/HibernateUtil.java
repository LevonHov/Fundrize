package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("fundrize");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
