<%@ page import="com.example.projetjavamanu.Etudiant" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>

    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<body>

    <jsp:useBean id="etudiants" type="java.util.List<com.example.projetjavamanu.Etudiant>" scope="request"/>

    <div class="container">

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
            Créer étudiant
        </button>

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
        <%@ include file="viewCreate.jsp" %>
        <%@include file="viewBarrePagination.jsp"%>
        <%@ include file="viewNavbar.jsp" %>


        <button id="btn_ajax" class="btn-primary">Ajax</button>

        <div class="app">

        </div>
    </div>







    </body>

    <script src="ressources/index.js"></script>
</html>