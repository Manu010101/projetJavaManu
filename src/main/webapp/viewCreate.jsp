<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="com.example.projetjavamanu.Groupe" %>
<jsp:useBean id="groupes" type="java.util.List<com.example.projetjavamanu.Groupe>" scope="request"/>

            <div >

                <form method="post" action="<%= application.getContextPath()%>/do/save">
                    <div class="form-group">
                        <label>Nom
                            <input type="text" class="form-control" id="nom"  name="nom" placeholder="Enter nom">
                        </label>

                    </div>

                    <div class="form-group">
                        <label>Prénom
                            <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Enter prenom">
                        </label>

                    </div>

                    <div class="form-group">
                        <label>Moyenne
                            <input type="number" class="form-control" id="moyenne" name="moyenne" placeholder="Enter moyenne">
                        </label>

                    </div>

                    <div class="form-group">
                        <label>Absences
                            <input type="number" class="form-control" id="nbAbsences" name="nbAbsences"  placeholder="Enter nb absences">
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


