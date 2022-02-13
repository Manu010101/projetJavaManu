<%@ page import="com.example.projetjavamanu.Groupe" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="groupes" type="java.util.List<com.example.projetjavamanu.Groupe>" scope="request"/>

<h1>Editer les absences</h1>

<%--Filtrer par groupe--%>
<select class="filtre" aria-label="Default select example">
    <option selected>groupe</option>
    <% for (Groupe groupe: groupes) { %>
    <option><%= groupe.getNom()%></option>
    <% } %>
</select>



<div class="editNotes">

</div>

<script type='text/javascript'>
    // Description des urls de demande ajax
    var urlEditAbsences = "<%=application.getContextPath()%>/do/ajaxEtudiantsParGroupe";
    var urlSaveAbsences = "<%=application.getContextPath()%>/do/saveAbsences";


</script>
<script src="<%=application.getContextPath()%>/ressources/js/editAbsences.js"></script>