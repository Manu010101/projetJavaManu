package com.example.projetjavamanu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

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

        if (action.equals("/accueil")){
            request.setAttribute("content", "/viewAccueil.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
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

            int index = 1;
            rendVuePaginee(request, response, index);

        }

        if (action.equals("/show")){
            System.out.println("passage dans show");

            int index = 1;
            rendVuePaginee(request, response, index);

        }

        if(action.equals("/destroy")){

            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("passage dans destroy");
            EtudiantDAO.destroy(id);
            int index = Integer.parseInt(request.getParameter("index"));
            rendVuePaginee(request, response, index);

        }

        if (action.equals("/edit")){
            long id = Long.parseLong(request.getParameter("id"));
            Etudiant etudiant = EtudiantDAO.findById(id);
            request.setAttribute("index", request.getParameter("index"));
            request.setAttribute("etudiant", etudiant);
            request.setAttribute("content", "/viewUpdateEtudiant.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
        }

        if(action.equals("/update")){

            long id = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            int index = Integer.parseInt(request.getParameter("index"));
            System.out.println("index dans update=" + index);

            int moyenne = Integer.parseInt(request.getParameter("moyenne"));
            int nbAbsences = Integer.parseInt(request.getParameter("nbAbsences"));

            EtudiantDAO.update(id, nom, prenom, moyenne,nbAbsences);
            rendVuePaginee(request, response, index);


        }

        if (action.equals("/page")){

            int index = Integer.parseInt(request.getParameter("index"));
            System.out.println("passage dans ctrlr page avec index = " + index);

            rendVuePaginee(request, response, index);
        }

        /**
         * Fonction call quand requete Ajax pour filtrer les étudiants selon plusieurs paramètres
         */
        if (action.equals("/ajax")){
            System.out.println("entree dans async controleur");

            //Récupération des paramètres pour filtrer en async
            Enumeration<String> params_names = request.getParameterNames();
            Map<String, String> parametres = new HashMap<>();

            while (params_names.hasMoreElements()){
                String param_name = params_names.nextElement();
                parametres.put(param_name, request.getParameter(param_name));
            }

            System.out.println("paramètres" + parametres);

            List<Etudiant> etudiants = EtudiantDAO.queryFiltree(parametres);

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(etudiants);
            System.out.println("json=" + json);
            // Retourne le résultat sous forme JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        /**
         * Fonction qui retourne les étudiants filtrés par un groupe
         */
        if (action.equals("/ajaxEtudiantsParGroupe")){

            Groupe groupe = GroupeDAO.findByNom(request.getParameter("nomGroupe"));
            int groupe_id = Math.toIntExact(groupe.getId());

            List<Etudiant> etudiants = EtudiantDAO.getEtudiantsByGroupe(groupe_id);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(etudiants);
            System.out.println("json=" + json);
            // Retourne le résultat sous forme JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);


        }

        if (action.equals("/consulterNotes")){
            System.out.println("entrer dans controleur consulter notes");
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewConsulteNotes.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
        }

        if (action.equals("/editNotes")){
            System.out.println("entrer dans controleur editNotes");
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewEditNotes.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);

        }

        if (action.equals("/saveNotes")){
            System.out.println("entrée dans saveNotes");
            Enumeration<String> ids = request.getParameterNames();
            String id = null;
            Long id_long = null;
            String note = null;
            while (ids.hasMoreElements()){
                id = ids.nextElement();
                id_long = Long.valueOf(id);
                EtudiantDAO.updateNote(id_long, Integer.parseInt(request.getParameter(id)));
            }
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewConsulteNotes.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
        }

        if (action.equals("/consulterAbsences")){
            System.out.println("entrer dans controleur consulter absences");
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewConsulteAbsences.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
        }

        if (action.equals("/editerAbsences")){
            System.out.println("entrer dans controleur editAbsences");
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewEditAbsences.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
            }

        if (action.equals("/saveAbsences")){
            System.out.println("entrée dans saveAbsences");
            Enumeration<String> ids = request.getParameterNames();
            String id = null;
            Long id_long = null;
            String note = null;
            while (ids.hasMoreElements()){
                id = ids.nextElement();
                id_long = Long.valueOf(id);
                EtudiantDAO.updateAbsence(id_long, Integer.parseInt(request.getParameter(id)));
                }
            List<Groupe> groupes = GroupeDAO.getAll();
            request.setAttribute("groupes", groupes);
            request.setAttribute("content", "/viewConsulteAbsences.jsp");
            request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
        }
        /**
         * Partie qui gère le CRUD groupe
         */
        if (uri.contains("groupe")){
//            TODO: avec utilisation de switch pb de scope - on peut pas redefinir groupes...
            System.out.println("passage dans groupe");
            Map<String, String[]> parametres = request.getParameterMap();
            String action2 = parametres.get("action")[0];
            System.out.println("action2 :" + action2);
            List<Groupe> groupes = null;

            switch (action2){
                case "show":
                    groupes = GroupeDAO.getAll();
                    request.setAttribute("groupes", groupes);
                    request.setAttribute("content", "/viewGroupes.jsp");
                    request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
                    break;

                case "create":

                    request.setAttribute("content", "/viewCreateGroupe.jsp");
                    request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
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
                    request.setAttribute("content", "/viewUpdateGroupe.jsp");
                    request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
                    break;

                case "update":

                    GroupeDAO.update(Long.parseLong(request.getParameter("id")), request.getParameter("nom"));
                    response.sendRedirect(request.getRequestURL() + "?action=show");
                    break;

                case "destroy":


                    int id = Integer.parseInt(parametres.get("id")[0]);

                    if (GroupeDAO.findById(id).getListe().size() > 0){
                        ArrayList<String> messageErreur = new ArrayList<>();
                        messageErreur.add("Pas possible de détruire un groupe avec des étudiants");
                        request.setAttribute("messageErreur", messageErreur);
                        groupes = GroupeDAO.getAll();
                        request.setAttribute("groupes", groupes);
                        request.setAttribute("content", "/viewGroupes.jsp");
                        request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
                        break;
                    };
                    GroupeDAO.destroy(id);

                    response.sendRedirect(request.getRequestURL() + "?action=show");
                    break;




            }
        }

    }

    /**
     * Fonction qui retoune une jsp contenant les étudiants paginés
     * Transporte des informations (via des setAttribute) pour pouvoir filtrer
     * @param request
     * @param response
     * @param index n° de page demandée
     * @throws ServletException
     * @throws IOException
     */
    private void rendVuePaginee(HttpServletRequest request, HttpServletResponse response, int index) throws ServletException, IOException {
        List<Etudiant> etudiants = EtudiantDAO.getPage(index);
        int nbPages = this.calculerNbPages();
        request.setAttribute("nbPages", nbPages);

        List<Groupe> groupes = GroupeDAO.getAll();

        request.setAttribute("index", index);
        request.setAttribute("groupes", groupes);
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("content", "/viewEtudiants.jsp");


        //Partie pour donner les valeurs possibles des filtres

        //filtre sur les noms
        ArrayList<Character> lettres = new ArrayList<>();
        for (Etudiant etudiant:EtudiantDAO.getAll()) {
            String nom = etudiant.getNom();
            System.out.println(nom);
            char lettre = nom.charAt(0);
            if (!lettres.contains(lettre)){
                lettres.add(lettre);
            }
        }
        request.setAttribute("lettres", lettres);

        //filtre sur les notes
        ArrayList<Integer> moyennes = new ArrayList<>();
        for (Etudiant etudiant:EtudiantDAO.getAll()) {
            int moyenne = etudiant.getMoyenne();
            if (!moyennes.contains(moyenne)){
                moyennes.add(moyenne);
            }
        }
        request.setAttribute("moyennes", moyennes);

        request.getServletContext().getRequestDispatcher("/viewLayout.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        doGet(request, response);

    }

    public void destroy() {
    }

    public int calculerNbPages(){

        int nbEtudiants = EtudiantDAO.count();
        int nbEtudiantsPage = 3;
        int nbPages = (int) Math.ceil((float) nbEtudiants / nbEtudiantsPage);
        return nbPages;
    }
}
