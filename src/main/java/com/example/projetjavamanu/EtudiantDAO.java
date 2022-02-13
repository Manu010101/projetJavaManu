package com.example.projetjavamanu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EtudiantDAO {

    private static final  String PERSISTENCE_UNIT_NAME = "PU_MYSQL";

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

    public static void updateNote(long id, int moyenne){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Etudiant etudiant =  em.find(Etudiant.class, id);;


        etudiant.setMoyenne(moyenne);

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

        int nbEltParPage = 3;
        int firstElt = (indexPage >  0) ?  (indexPage - 1) * nbEltParPage :  0
        ;
        Query q = em.createQuery("SELECT e FROM Etudiant e")
                .setMaxResults(nbEltParPage)
                .setFirstResult(firstElt);
        System.out.println("query page: " + q);

        List<Etudiant> etudiants = q.getResultList();
        return etudiants;
    }

    /**
     * Retourne le nb d'Etudiants en bd
     * @return
     */
    public static int count(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createNativeQuery("SELECT COUNT(*) FROM ETUDIANT;");
        Long nbEltsLong = (Long) q.getResultList().get(0);
        int nbElts = nbEltsLong.intValue();
        System.out.println("nb d elts dans bd:" + q.getResultList().get(0));

        return nbElts;

    }

    public static List<Etudiant> getAll() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Etudiant e");

        return q.getResultList();
    }

    /**
     * Fonction qui filtre les étudiants en fonction des attributs contenus dans params
     * @param params : contient (dans un dico: champ + valeur) les champs selon lesquels filtrer
     * @return
     */
    public static List<Etudiant> queryFiltree(Map<String, String> params){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        String raw = "SELECT * FROM Etudiant WHERE (";
        ArrayList<String> conditions = new ArrayList<>();
        //remplissage d'un tableau contenant les bouts de conditions
        for (String key:params.keySet()) {
            if (key.equals("nom")){
                conditions.add(" Etudiant.nom LIKE '?" + key + "%'");
            }
            else {
                conditions.add(" Etudiant." + key + " = ?"+key);
            }
        }
        if (conditions.size() > 1) {
            for (String cdt : conditions) {
                raw += " AND " + cdt ;
            }
        }
        else raw +=  conditions.get(0);

        raw += ")";

        Query q = em.createNativeQuery(raw);

        for (String key:params.keySet()) {
            q.setParameter(key, params.get(key));
        }


        System.out.println("query = " + raw);

        return q.getResultList();


    }

    /**
     * Fonction vérifiant si nom et prénom sont déjà dans la BD auquel cas renvoie true
     * @param nom
     * @param prenom
     * @return boolean
     */
    public static boolean verifieSiDejaEnregistre(String nom, String prenom){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createNativeQuery("SELECT COUNT(*) FROM ETUDIANT WHERE (ETUDIANT.NOM = ? AND ETUDIANT.PRENOM = ?)")
            .setParameter(1, nom)
            .setParameter(2, prenom);

        Long nbEltsLong = (Long) q.getResultList().get(0);
        int nbElts = nbEltsLong.intValue();

        return nbElts > 0;
    }


    public static List<Etudiant> getEtudiantsByGroupe(int groupe_id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Query q = em.createNativeQuery("SELECT * FROM ETUDIANT WHERE ETUDIANT.GROUPE_ID = ?")
                .setParameter(1, groupe_id);

        return q.getResultList();
    }

    public static void updateAbsence(Long id, int absence) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Etudiant etudiant =  em.find(Etudiant.class, id);;


        etudiant.setNbAbsences(absence);

        em.getTransaction().commit();
        em.close();
    }
}
