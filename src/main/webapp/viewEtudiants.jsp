<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="com.example.projetjavamanu.Etudiant" %>
<%@ page import="com.example.projetjavamanu.Groupe" %>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id="etudiants" type="java.util.List<com.example.projetjavamanu.Etudiant>" scope="request"/>
<jsp:useBean id="groupes" type="java.util.List<com.example.projetjavamanu.Groupe>" scope="request"/>

    <div class="container">

        <div>

            <a href="<%= application.getContextPath()%>/do/create" class="btn-primary">Créer étudiant</a>

            <select class="filtre" aria-label="Default select example">
                <option selected>groupe</option>
                <% for (Groupe groupe: groupes) { %>
                <option><%= groupe.getNom()%></option>
                <% } %>
            </select>

            <select class="filtre" aria-label="Default select example">
                <option selected>nom</option>
                <%
                     ArrayList<Character> lettres = new ArrayList<>();
                    for (Etudiant etudiant:etudiants) {
                        String nom = etudiant.getNom();
                        System.out.println(nom);
                        char lettre = nom.charAt(0);
                        if (!lettres.contains(lettre)){
                            lettres.add(lettre);
                        }
                    }
                    if (lettres.size() > 1){

                        for (Character l:lettres) {
                            System.out.println("ch" + l);
                        }}

                        for (Character l:lettres) { %>
                            <option><%= l %></option>
                    <%
                    } %>
            </select>

            <select class="filtre" aria-label="Default select example">
                <option selected>moyenne</option>
                <% for (Etudiant etudiant: etudiants) { %>
                <option><%= etudiant.getMoyenne() %></option>
                <% } %>
            </select>

        </div>
        <div class="principale">
            <table class="table">
                <thead>
                <tr>
                    <td>Nom</td>
                    <td>Prénom</td>
                    <td>Moyenne</td>
                    <td>Nombre d'absences</td>
                    <td>Groupe</td>
                    <td>Modifier</td>
                    <td>Supprimer</td>
                </tr>
                </thead>

                <tbody>
                <% for (Etudiant etudiant: etudiants) { %>

                <tr>
                    <td><%=etudiant.getNom()%></td>
                    <td><%=etudiant.getPrenom()%></td>
                    <td><%=etudiant.getMoyenne()%></td>
                    <td><%=etudiant.getNbAbsences()%></td>
                    <td><%=etudiant.getGroupe().getNom()%></td>
                    <td><a href="<%= application.getContextPath()%>/do/edit?id=<%= etudiant.getId() %>&index=<%= request.getAttribute("index") %>" class="btn btn-info">maj</a></td>
                    <td><a href="<%= application.getContextPath()%>/do/destroy?id=<%= etudiant.getId() %>&index=<%= request.getAttribute("index") %>" class="btn btn-danger">Supprimer</a></td>
                </tr>

                <% } %>

                </tbody>
            </table>
        </div>

        <%@ include file="viewBarrePagination.jsp"%>

    </div>
    <script type='text/javascript'>
        // Description des urls de demande ajax
        var urlgetetudiants = "<%=application.getContextPath()%>/do/ajax";
    </script>
    <script src="<%=application.getContextPath()%>/ressources/js/app.js"></script>
