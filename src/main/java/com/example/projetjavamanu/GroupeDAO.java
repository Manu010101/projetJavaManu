package com.example.projetjavamanu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class GroupeDAO {

    private static final  String PERSISTENCE_UNIT_NAME = "PU_MYSQL";

    public static Groupe findById(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        Groupe g = em.find(Groupe.class, id);
        em.close();
        return g;
    }

    public static Groupe findByNom(String nom){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        final String QUERY = "SELECT g FROM Groupe g WHERE g.nom = :nom";
        Query query = em.createQuery(QUERY);
        query.setParameter("nom", nom);
        Groupe g = (Groupe) query.getSingleResult();
        em.close();
        return g;
    }

    public static void create(Groupe g){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        em.close();
    }

    public static void destroy(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Groupe AS g WHERE g.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public static void update(long id, String nom){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Groupe groupe =  em.find(Groupe.class, id);;

        groupe.setNom(nom);

        em.getTransaction().commit();
        em.close();
    }

    public static List<Groupe> getAll() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT g FROM Groupe g");

        return q.getResultList();
    }
}
