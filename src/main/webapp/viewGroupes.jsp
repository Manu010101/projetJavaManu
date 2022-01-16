<%@ page import="com.example.projetjavamanu.Groupe" %><%--
  Created by IntelliJ IDEA.
  User: manuel
  Date: 15/01/2022
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groupes" type="java.util.List<com.example.projetjavamanu.Groupe>" scope="request"/>
<html>
<head>

    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<body>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
    Créer groupe
</button>

<table class="table">
    <thead>
    <tr>
        <td>Nom</td>
        <td>Etudiants</td>
        <td>maj</td>
        <td>suppr</td>
    </tr>
    </thead>

    <tbody>
    <% for (Groupe groupe: groupes) { %>

    <tr>
        <td><%=groupe.getNom()%></td>
        <td><%=groupe.getListe()%></td>
        <td><a href="<%= application.getContextPath()%>/groupe/?action=edit&id=<%= groupe.getId() %>" class="btn btn-info">maj</a></td>
        <td><a href="<%= application.getContextPath()%>/groupe/?action=destroy&id=<%= groupe.getId() %>" class="btn btn-danger">Supprimer</a></td>
    </tr>

    <% } %>

    </tbody>
</table>

<%@ include file="viewCreateGroupe.jsp" %>

<%@ include file="viewNavbar.jsp" %>

</body>
</html>
