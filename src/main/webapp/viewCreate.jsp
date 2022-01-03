<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


</head>
<body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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



        <input type="submit" class="btn btn-primary" value="Valider">
    </form>

</body>
</html>