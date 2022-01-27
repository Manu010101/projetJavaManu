

<nav class="navbar navbar-expand-lg navbar-light bg-light">


    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item active">
                <a class="nav-link" href="#">Accueil <span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<%= application.getContextPath()%>/do/show" class="btn btn-primary">Etudiants</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<%= application.getContextPath()%>/groupe/?action=show">Groupes</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Consulter les absences</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Editer les absences</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Notes
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Consulter les notes</a>
                    <a class="dropdown-item" href="#">Editer les notes</a>

                </div>
            </li>


        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
