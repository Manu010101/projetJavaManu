<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <jsp:useBean id="etudiant" type="com.example.projetjavamanu.Etudiant" scope="request"/>

    <form method="post" action="<%= application.getContextPath()%>/do/update?id=<%=etudiant.getId()%>">
    <div class="form-group">
        <label>Nom
            <input type="text" class="form-control" id="nom"  name="nom" placeholder="Enter nom" value="<%= etudiant.getNom() %>">
        </label>

    </div>

    <div class="form-group">
        <label>Prénom
            <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Enter prenom" value="<%= etudiant.getPrenom() %>">
        </label>

    </div>

    <div class="form-group">
        <label>Moyenne
            <input type="number" class="form-control" id="moyenne" name="moyenne" placeholder="Enter moyenne" value="<%= etudiant.getMoyenne() %>">
        </label>

    </div>

    <div class="form-group">
        <label>Absences
            <input type="number" class="form-control" id="nbAbsences" name="nbAbsences"  placeholder="Enter nb absences" value="<%= etudiant.getNbAbsences() %>">
        </label>

    </div>



    <input type="submit" class="btn btn-primary" value="Valider">
</form>

</body>
</html>