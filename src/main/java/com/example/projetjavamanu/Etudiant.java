package com.example.projetjavamanu;


import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import org.eclipse.persistence.annotations.PrimaryKey;

import java.io.Serializable;

@Entity
public class Etudiant implements Serializable {

    public Etudiant(){}

    public Etudiant(String nom, String prenom, int nbAbsences, int moyenne, Groupe groupe) {
        this.nom = nom;
        this.prenom = prenom;
        this.nbAbsences = nbAbsences;
        this.moyenne = moyenne;
        this.groupe = groupe;
    }

    @Id
    @GeneratedValue
    @Expose
    private Long id;

    @Expose
    private String nom;
    @Expose
    private String prenom;
    @Expose
    private int nbAbsences;
    @Expose
    private int moyenne;

    @ManyToOne
    private  Groupe groupe;



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

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
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
