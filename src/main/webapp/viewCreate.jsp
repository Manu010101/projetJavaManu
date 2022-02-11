<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="com.example.projetjavamanu.Groupe" %>
<jsp:useBean id="groupes" type="java.util.List<com.example.projetjavamanu.Groupe>" scope="request"/>

            <div >

                <form method="post" action="<%= application.getContextPath()%>/do/save">
                    <div class="form-group">
                        <label>Nom
                            <input type="text" class="form-control" id="nom"  name="nom" placeholder="Enter nom"
                            <% if (request.getAttribute("nom") != null) {%>
                                value="<%= request.getAttribute("nom")%>"
                            <% }%>
                            >
                        </label>

                    </div>

                    <div class="form-group">
                        <label>Prénom
                            <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Enter prenom"
                                <% if (request.getAttribute("prenom") != null) {%>
                                   value="<%= request.getAttribute("prenom")%>"
                                <% }%>
                            >
                        </label>

                    </div>

                    <div class="form-group">
                        <label>Moyenne
                            <input type="number" class="form-control" id="moyenne" name="moyenne" placeholder="Enter moyenne"
                                <% if (request.getAttribute("moyenne") != null) {%>
                                   value="<%= request.getAttribute("moyenne")%>"
                                <% }%>
                            >
                        </label>

                    </div>

                    <div class="form-group">
                        <label>Absences
                            <input type="number" class="form-control" id="nbAbsences" name="nbAbsences"  placeholder="Enter nb absences"
                                <% if (request.getAttribute("nbAbsences") != null) {%>
                                   value="<%= request.getAttribute("nbAbsences")%>"
                                <% }%>
                            >
                        </label>

                    </div>

                    <div class="form-group">
                        <label>Groupe
                            <select class="form-control" id="exampleFormControlSelect1" name="nomGroupe">
                                <% for (Groupe groupe: groupes) { %>
                                    <option><%=groupe.getNom()%></option>
                                <% }%>
                            </select>
                        </label>

                    </div>

                    <input type="submit" class="btn btn-primary" value="Valider">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>

                </form>

            </div>


