<%@ page import="com.example.projetjavamanu.Etudiant" %>
<%@ page import="com.example.projetjavamanu.Groupe" %>
<%@ page import="jakarta.persistence.EntityManagerFactory" %>
<%@ page import="jakarta.persistence.Persistence" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="jakarta.persistence.EntityTransaction" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<%
    Etudiant e1 = new Etudiant();
    e1.setNom("ManuEL").setNbAbsences(1).setMoyenne(12);
    System.out.println(e1);



    Groupe g1 = new Groupe();
    g1.setNom("Simo").add(e1);

    System.out.println(g1);

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdTest");

    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    tx.begin();
    em.persist(e1);
    em.persist(g1);
    tx.commit();
    em.close();
    emf.close();

%>

<body>

    <div class="container">

        <table class="table">

        </table>

        <p><%= "etudiant 1: " + e1 %></p>

        <%= g1 %>



        <div>
            <a href="do/create" class="btn btn-primary">créer étudiant</a>
            <a href="do/update" class="btn btn-primary">Modifier étudiant</a>
            <a href="do/show" class="btn btn-primary">Liste étudiants</a>
            <a href="do/destroy" class="btn btn-primary">Supprimer étudiant</a>
        </div>



    </div>



    <%@ include file="viewNavbar.html" %>
</body>
</html>