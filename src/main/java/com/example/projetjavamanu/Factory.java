package com.example.projetjavamanu;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Factory {

    private static final  String PERSISTENCE_UNIT_NAME = "PU_SQLITE";

    public static EntityManagerFactory factory;


    // Creation de la factory
    public static void open() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ;
    }

    // Fermeture de la factory
    public static void close() {
        factory.close();
    }

}
