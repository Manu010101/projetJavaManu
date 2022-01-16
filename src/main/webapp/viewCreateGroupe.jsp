
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Création d'un groupe</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <form method="post" action="<%= application.getContextPath()%>/groupe/?action=save">
                    <div class="form-group">
                        <label>Nom
                            <input type="text" class="form-control" id="nom"  name="nom" placeholder="Enter nom">
                        </label>

                    </div>

                    <input type="submit" class="btn btn-primary" value="Valider">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>

                </form>


            </div>

        </div>
    </div>
</div>