<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="com.example.projetjavamanu.Etudiant" %>
<%@ page import="com.example.projetjavamanu.Groupe" %>
<jsp:useBean id="etudiants" type="java.util.List<com.example.projetjavamanu.Etudiant>" scope="request"/>
<jsp:useBean id="groupes" type="java.util.List<com.example.projetjavamanu.Groupe>" scope="request"/>

    <div class="container">

        <div>

            <a href="<%= application.getContextPath()%>/do/create" class="btn-primary">Créer étudiant</a>

            <select class="form-select" aria-label="Default select example">
                <option selected>Choix du groupe</option>
                <% for (Groupe groupe: groupes) { %>
                    <option><%= groupe.getNom() %></option>
                <% } %>
            </select>

            <select class="form-select" aria-label="Default select example">
                <option selected>Noms</option>
                <% for (Groupe groupe: groupes) { %>
                <option><%= groupe.getNom() %></option>
                <% } %>
            </select>

            <select class="form-select" aria-label="Default select example">
                <option selected>Notes</option>
                <% for (Groupe groupe: groupes) { %>
                <option><%= groupe.getNom() %></option>
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
                    <td><a href="<%= application.getContextPath()%>/do/edit?id=<%= etudiant.getId() %>" class="btn btn-info">maj</a></td>
                    <td><a href="<%= application.getContextPath()%>/do/destroy?id=<%= etudiant.getId() %>" class="btn btn-danger">Supprimer</a></td>
                </tr>

                <% } %>

                </tbody>
            </table>
        </div>

        <%@ include file="viewBarrePagination.jsp"%>

    </div>
