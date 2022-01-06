package com.example.projetjavamanu;


import jakarta.persistence.*;
import org.eclipse.persistence.annotations.PrimaryKey;

@Entity
public class Etudiant {

    public Etudiant(){}

    public Etudiant(String nom, String prenom, int nbAbsences, int moyenne) {
        this.nom = nom;
        this.prenom = prenom;
        this.nbAbsences = nbAbsences;
        this.moyenne = moyenne;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    private int nbAbsences;
    private int moyenne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


//  GETTERS AND SETTERS

    public String getNom() {
        return nom;
    }

    public Etudiant setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public Etudiant setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public int getNbAbsences() {
        return nbAbsences;
    }

    public Etudiant setNbAbsences(int nbAbsences) {
        this.nbAbsences = nbAbsences;
        return this;
    }

    public int getMoyenne() {
        return moyenne;
    }

    public Etudiant setMoyenne(int moyenne) {
        this.moyenne = moyenne;
        return this;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nbAbsences=" + nbAbsences +
                ", moyenne=" + moyenne +
                '}';
    }
}
