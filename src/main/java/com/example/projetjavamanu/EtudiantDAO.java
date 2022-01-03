package com.example.projetjavamanu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EtudiantDAO {

    public static Etudiant findById(int id){
        EntityManager em =  Factory.factory.createEntityManager();

        Etudiant e = em.find(Etudiant.class, id);
        return e;
    }

    public static void create(Etudiant e){
        EntityManager em = Factory.factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    public static void destroy(Etudiant e){
        EntityManager em = Factory.factory.createEntityManager();

        em.getTransaction().begin();
        em.find(Etudiant.class, e.getId());
        em.remove(e);

        em.getTransaction().commit();

        em.close();
    }


}
