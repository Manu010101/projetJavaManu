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

    /**
     * Retourne les etudiants correspondants à une page
     * @param indexPage
     * @return List<Etudiant>
     */
    public static List<Etudiant> getPage(int indexPage){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        int nbEltParPage = 2;
        int firstElt = indexPage * nbEltParPage;
//        TODO: pb = SQLite only supports TYPE_FORWARD_ONLY cursors solu = changer de bd, mysql?
        Query q = em.createNativeQuery("SELECT * FROM Etudiant")
                .setMaxResults(nbEltParPage)
                .setFirstResult(firstElt);
        System.out.println("query page: " + q);

        List<Etudiant> etudiants = q.getResultList();
        System.out.println("etudiants paginés " + etudiants);
        return etudiants;
    }

    /**
     * Retourne le nb d'Etudiants en bd
     * @return
     */
    public static int count(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createNativeQuery("COUNT * FROM Etudiant");
        System.out.println("nb d elts dans bd:" + q.getFirstResult());
        return q.getFirstResult();

    }

    public static List<Etudiant> getAll() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Etudiant e");

        return q.getResultList();
    }
}
