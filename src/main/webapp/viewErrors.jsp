
<jsp:useBean id="messageErreur" type="java.util.ArrayList" scope="request"></jsp:useBean>


<div class="alert alert-danger" role="alert">
    <% for (Object message: messageErreur) { %>
        <p><%= message %></p>
    <% } %>

</div>


