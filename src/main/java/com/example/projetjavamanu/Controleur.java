package com.example.projetjavamanu;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class Controleur extends HttpServlet {


    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action =request.getPathInfo();
        System.out.println(action);

        if (action.equals("/create")){
            System.out.println("passage dans create");
            getServletConfig().getServletContext().getRequestDispatcher("/viewCreate.jsp").forward(request, response);
        }


        if(action.equals("/destroy")){
            System.out.println("passage dans destroy");
        }


        else{
            System.out.println("autre");
            System.out.println(action);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String action =request.getPathInfo();
        System.out.println(action);

        if (action.equals("/save")){
            System.out.println("passage dans save etudiant");
            System.out.println("nom: " + request.getParameter("nom"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            System.out.println(request.getParameter("moyenne"));

            int moyenne = Integer.parseInt(request.getParameter("moyenne"));
            int nbAbsences = Integer.parseInt(request.getParameter("nbAbsences"));

            Etudiant e = new Etudiant(nom, prenom, nbAbsences, moyenne);

            EtudiantDAO.create(e);

        }

    }

    public void destroy() {
    }
}