package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {

        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
    }
}