package com.example.projetjavamanu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class EtudiantDAO {

    private static final  String PERSISTENCE_UNIT_NAME = "PU_SQLITE";

    public static Etudiant findById(long id){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        Etudiant e = em.find(Etudiant.class, id);
        em.close();
        return e;
    }

    public static void create(Etudiant e){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    public static void destroy(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Etudiant AS e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public static void update(long id, String nom, String prenom, int moyenne, int nbAbsences){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Etudiant etudiant =  em.find(Etudiant.class, id);;

        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setMoyenne(moyenne);
        etudiant.setNbAbsences(nbAbsences);

        em.getTransaction().commit();
        em.close();

    }


    public static List<Etudiant> getAll() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Etudiant e");

        return q.getResultList();
    }
}
