package com.example.projetjavamanu;

import java.io.*;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class Controleur extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action =request.getPathInfo();
        String querystring = request.getQueryString();
        String uri = request.getRequestURI();
        String url = String.valueOf(request.getRequestURL());

        System.out.println("action: " + action);
        System.out.println("querystring: " + querystring);
        System.out.println("uri: " + uri);
        System.out.println("url: " + uri);

        if (action.equals("/acceuil")){
            RequestDispatcher rq = request.getRequestDispatcher("/viewLayout.jsp");
            rq.forward(request, response);
        }

        if (action.equals("/create")){
            System.out.println("passage dans create");
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewCreate.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
        }

        if (action.equals("/save")){
            System.out.println("passage dans save etudiant");
            System.out.println("nom: " + request.getParameter("nom"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            System.out.println(request.getParameter("moyenne"));

            int moyenne = Integer.parseInt(request.getParameter("moyenne"));
            int nbAbsences = Integer.parseInt(request.getParameter("nbAbsences"));

//          récupération du nom du groupe

            String nomGroupe = request.getParameter("nomGroupe");

//         Trouver l'entité groupe correspondante au nom
            Groupe g = GroupeDAO.findByNom(nomGroupe);


            Etudiant e = new Etudiant(nom, prenom, nbAbsences, moyenne,  g);

            EtudiantDAO.create(e);

            List<Etudiant> etudiants = EtudiantDAO.getAll();

            int nbPages = this.calculerNbPages();
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("etudiants", etudiants);
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewEtudiants.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);

        }
        //old
        if (action.equals("/show")){
            System.out.println("passage dans show");
            int nbPages = this.calculerNbPages();
            List<Etudiant> etudiants = EtudiantDAO.getAll();
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);

            // Ajouter les étudiants à la requête pour affichage
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("content", "/viewEtudiants.jsp");
            System.out.println("requete: bean" + request);
            System.out.println("/show etudiants:" + etudiants.toString());
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
        }

        if(action.equals("/destroy")){

            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("passage dans destroy");
            EtudiantDAO.destroy(id);
            List<Etudiant> etudiants = EtudiantDAO.getAll();
            List<Groupe> groupes = GroupeDAO.getAll();
            int nbPages = this.calculerNbPages();
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewEtudiants.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);

        }

        if (action.equals("/edit")){
            long id = Long.parseLong(request.getParameter("id"));
            Etudiant etudiant = EtudiantDAO.findById(id);
            request.setAttribute("etudiant", etudiant);
            request.setAttribute("content", "/viewUpdateEtudiant.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
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
            int nbPages = this.calculerNbPages();
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("etudiants", etudiants);
            request.setAttribute("content", "/viewEtudiants.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);


        }

        if (action.equals("/page")){

            int index = Integer.parseInt(request.getParameter("index"));
            System.out.println("passage dans ctrlr page avec index = " + index);

            List<Etudiant> etudiants = EtudiantDAO.getPage(index);
            int nbPages = this.calculerNbPages();
            request.setAttribute("nbPages", nbPages);

            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);

            request.setAttribute("etudiants", etudiants);
            request.getServletContext().getRequestDispatcher("/viewEtudiants.jsp").forward(request, response);
        }

        if (action.equals("ajax")){

        }

        if (uri.contains("groupe")){
//            TODO: avec utilisation de switch pb de scope - on peut pas redefinir groupes...
            System.out.println("passage dans groupe");
            Map<String, String[]> parametres = request.getParameterMap();
            String action2 = parametres.get("action")[0];
            System.out.println("action2 :" + action2);
            switch (action2){
                case "show":
                    List<Groupe> groupes = GroupeDAO.getAll();
                    request.setAttribute("groupes", groupes);
                    request.getServletContext().getRequestDispatcher("/viewGroupes.jsp").forward(request, response);
                    break;

                case "create":
                    request.getServletContext().getRequestDispatcher("/viewCreateGroupe.jsp").forward(request, response);
                    break;

                case "save":
                    String nom = request.getParameter("nom");
                    Groupe g = new Groupe(nom);
                    GroupeDAO.create(g);
                    String chemin = String.valueOf(request.getRequestURL()) + "?action=show";
                    response.sendRedirect(chemin);
                    break;

                case "edit":

                    Groupe groupe = GroupeDAO.findById(Long.parseLong(request.getParameter("id")));
                    request.setAttribute("groupe", groupe);
                    request.getServletContext().getRequestDispatcher("/viewUpdateGroupe.jsp").forward(request, response);

                case "update":

                    GroupeDAO.update(Long.parseLong(request.getParameter("id")), request.getParameter("nom"));
                    response.sendRedirect(request.getRequestURL() + "?action=show");
                    break;

                case "destroy":

                    System.out.println("paramètres: " + parametres);
                    int id = Integer.parseInt(parametres.get("id")[0]);
                    System.out.println("id: " + id);
                    GroupeDAO.destroy(id);

                    response.sendRedirect(request.getRequestURL() + "?action=show");
                    break;




            }
        }

        else {
            request.getServletContext().getRequestDispatcher("/index.jsp");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        doGet(request, response);

    }

    public void destroy() {
    }

    public int calculerNbPages(){

        int nbEtudiants = EtudiantDAO.count();
        int nbEtudiantsPage = 2;
        int nbPages = (int) Math.ceil((float) nbEtudiants / nbEtudiantsPage);
        return nbPages;
    }
}
//--
//TODO: Nettoyer le controleur:
// - passage à redirect
// - utilisation de switch pour Etudiant
// - inclure les beans groupes pour pagination (sinon crash)
//--