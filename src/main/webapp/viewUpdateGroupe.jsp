
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groupe" type="com.example.projetjavamanu.Groupe" scope="request"/>

<form method="post" action="<%= application.getContextPath()%>/groupe/?action=update&id=<%=groupe.getId()%>">
    <div class="form-group">
        <label>Nom
            <input type="text" class="form-control" id="nom"  name="nom" placeholder="Enter nom" value="<%= groupe.getNom() %>">
        </label>

    </div>

    <input type="submit" class="btn btn-primary" value="Valider">
</form>

</body>
</html>
