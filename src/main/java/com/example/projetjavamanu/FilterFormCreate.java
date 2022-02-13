package com.example.projetjavamanu;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class FilterFormCreate implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        ArrayList<String> messageErreur = new ArrayList<>();
        Enumeration params = request.getParameterNames();
        boolean erreur = false;
        String paramV = null;
        String paramN = null;

        //Vérifier si champs pas vides
        while (params.hasMoreElements()){
            paramN = (String) params.nextElement();
            paramV = request.getParameter(paramN);
            if (isEmpty(paramV)){
                erreur = true;
                messageErreur.add("Le champ " + paramN + " ne doit pas être vide");
            }
        }

        //Vérifier si moyenne comprise entre 0 et 20
        if (!isEmpty(request.getParameter("moyenne"))){
            int moyenne = Integer.parseInt(request.getParameter("moyenne"));
            if (moyenne < 0 || moyenne > 20 ){
                erreur = true;
                messageErreur.add("La moyenne doit être comprise entre 0 et 20");
            }
        }

        //Vérifier si les absences ne sont pas négatives
        if (!isEmpty(request.getParameter("nbAbsences"))){
            int absences = Integer.parseInt(request.getParameter("nbAbsences"));
            if (absences< 0 ){
                erreur = true;
                messageErreur.add("Les absences ne peuvent êtres négatives");
            }
        }

        //Vérifier si etu n'est pas déjà dans la BD
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        if (EtudiantDAO.verifieSiDejaEnregistre(nom, prenom)){
            erreur = true;
            messageErreur.add("Etudiant déjà enregistré");
        }


        //chainage de la requete ou redirection si erreur
        if (!erreur) {
            System.out.println("passage dans doFilter");
            chain.doFilter(request, response);
        }
        else {
            request.setAttribute("messageErreur", messageErreur);
            reject( request,  response);
        }
    }

    public boolean isEmpty(String param){
        return param.length() < 1;
    }

    private void reject(ServletRequest request, ServletResponse response) throws ServletException, IOException{


        Enumeration params = request.getParameterNames();
        String paramN = null;

        while (params.hasMoreElements()){
            paramN = (String) params.nextElement();
            request.setAttribute(paramN, request.getParameter(paramN));
            System.out.println(paramN);
        }
        System.out.println("entree dans reject");
        List<Groupe> groupes = GroupeDAO.getAll();

        request.setAttribute("groupes", groupes);
        request.setAttribute("content", "/viewCreate.jsp");
        request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);


    }
}
