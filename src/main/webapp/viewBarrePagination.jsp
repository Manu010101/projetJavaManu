<jsp:useBean id="nbPages" type="java.lang.Integer" scope="request"/>

<nav aria-label="Page navigation example">
    <ul class="pagination">

        <% for (int i = 1; i < nbPages + 1; i++) { %>
            <li class="page-item"><a class="page-link" href="<%= application.getContextPath()%>/do/page?index=<%= i %>">page <%= i%></a></li>
        <%}%>

    </ul>
</nav>
