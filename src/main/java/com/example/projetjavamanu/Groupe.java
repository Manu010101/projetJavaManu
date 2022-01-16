package com.example.projetjavamanu;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Groupe {

    public Groupe() {
    }

    public Groupe(String nom) {
        this.nom = nom;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String nom;

    @OneToMany
    private ArrayList<Etudiant> liste;

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

    public Groupe setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public ArrayList<Etudiant> getListe() {
        return liste;
    }

    public String add(Etudiant e){

        if (liste == null){
            liste = new ArrayList<Etudiant>();
        }
        if (e != null){
            this.getListe().add(e);
            return "void";
        }

        return "non";

    }

    @Override
    public String toString() {
        return "Groupe{" +
                "nom='" + nom + '\'' +
                ", liste=" + liste +
                '}';
    }
}
