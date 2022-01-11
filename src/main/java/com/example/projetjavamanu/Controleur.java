package com.example.projetjavamanu;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class Controleur extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action =request.getPathInfo();
        System.out.println(action);

        if (action.equals("/create")){
            System.out.println("passage dans create");

            RequestDispatcher rq = request.getRequestDispatcher("/viewCreate.jsp");
            rq.forward(request, response);
        }

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

            List<Etudiant> etudiants = EtudiantDAO.getAll();
            request.setAttribute("etudiants", etudiants);
            RequestDispatcher rq = request.getServletContext().getRequestDispatcher("/viewEtudiants.jsp");
            rq.forward(request, response);

        }

        if (action.equals("/show")){
            System.out.println("passage dans show");
            List<Etudiant> etudiants = EtudiantDAO.getAll();

            // Ajouter les étudiants à la requête pour affichage
            request.setAttribute("etudiants", etudiants);
            System.out.println("requete: bean" + request);
            System.out.println("/show etudiants:" + etudiants.toString());
            request.getServletContext().getRequestDispatcher("/viewEtudiants.jsp").forward(request, response);
        }

        if(action.equals("/destroy")){

            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("passage dans destroy");
            EtudiantDAO.destroy(id);
            List<Etudiant> etudiants = EtudiantDAO.getAll();
            request.setAttribute("etudiants", etudiants);
            request.getServletContext().getRequestDispatcher("/viewEtudiants.jsp").forward(request, response);
        }

        if (action.equals("/edit")){
            long id = Long.parseLong(request.getParameter("id"));
            Etudiant etudiant = EtudiantDAO.findById(id);
            request.setAttribute("etudiant", etudiant);
            request.getServletContext().getRequestDispatcher("/viewUpdateEtudiant.jsp").forward(request, response);
        }

        if(action.equals("/update")){

            long id = Long.parseLong(request.getParameter("id"));
            System.out.println("passage dans update avec id = " + id);

            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");

            int moyenne = Integer.parseInt(request.getParameter("moyenne"));
            int nbAbsences = Integer.parseInt(request.getParameter("nbAbsences"));

            EtudiantDAO.update(id, nom, prenom, moyenne,nbAbsences);
            List<Etudiant> etudiants = EtudiantDAO.getAll();
            request.setAttribute("etudiants", etudiants);
            request.getServletContext().getRequestDispatcher("/viewEtudiants.jsp").forward(request, response);

        }

        if (action.equals("/count")){
            System.out.println("Entrée dans ctrlr count");
            EtudiantDAO.count();
        }

        if (action.equals("/page")){

            int index = Integer.parseInt(request.getParameter("index"));
            System.out.println("passage dans ctrlr page avec index = " + index);

            List<Etudiant> etudiants = EtudiantDAO.getPage(index);

            request.setAttribute("etudiants", etudiants);
            request.getServletContext().getRequestDispatcher("/viewEtudiants.jsp").forward(request, response);
        }

        else{
            System.out.println("die");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        doGet(request, response);

    }

    public void destroy() {
    }
}