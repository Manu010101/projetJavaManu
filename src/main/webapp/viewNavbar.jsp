

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item active">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/accueil">Accueil <span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/show" class="btn btn-primary">Etudiants</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<%= application.getContextPath()%>/groupe/?action=show">Groupes</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAbsences" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Absences
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<%= application.getContextPath()%>/do/consulterAbsences">Consulter les Absences</a>
                    <a class="dropdown-item" href="<%= application.getContextPath()%>/do/editerAbsences">Editer les Absences</a>

                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Notes
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<%= application.getContextPath()%>/do/consulterNotes">Consulter les notes</a>
                    <a class="dropdown-item" href="<%= application.getContextPath()%>/do/editNotes">Editer les notes</a>

                </div>
            </li>


        </ul>

    </div>
</nav>

