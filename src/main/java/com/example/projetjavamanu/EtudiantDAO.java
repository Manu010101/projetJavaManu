package com.example.projetjavamanu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {

    public static Etudiant findById(int id){
        EntityManager em =  Factory.factory.createEntityManager();

        Etudiant e = em.find(Etudiant.class, id);
        return e;
    }

    public static void create(Etudiant e){

        String PERSISTENCE_UNIT_NAME = "PU_SQLITE";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    public static void destroy(int id){
        String PERSISTENCE_UNIT_NAME = "PU_SQLITE";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Etudiant AS e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }


    public static List<Etudiant> getAll() {
        String PERSISTENCE_UNIT_NAME = "PU_SQLITE";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Etudiant e");

        return q.getResultList();
    }
}
