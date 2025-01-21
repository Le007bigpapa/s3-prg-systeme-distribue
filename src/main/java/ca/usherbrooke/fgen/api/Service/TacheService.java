package ca.usherbrooke.fgen.api.Service;
import ca.usherbrooke.fgen.api.business.ParticipatingUser;
import ca.usherbrooke.fgen.api.business.Tache;
import ca.usherbrooke.fgen.api.mapper.TacheMapper;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TacheService
{

    @Inject
    private TacheMapper tacheMapper;

    // Sélectionner une tâche
    @GET
    @Path("gettaches/{cip}/{idProjet}")
    public List<Tache> getTaches(
            @PathParam("cip") String cipUsager,
            @PathParam("idProjet") Integer idProjet
    )
    {

        return tacheMapper.selectTache(cipUsager, idProjet);
    }

    // Ajouter une tâche
    @GET
    @Path("addtache/{titre}/{description}/{idProjet}/{cipCreateur}/{idTacheParent}/{cip}")
    public List<Tache> addTache(
            @PathParam("titre") String titre,
            @PathParam("description") String description,
            @PathParam("idProjet") Integer idProjet,
            @PathParam("cipCreateur") String cipCreateur,
            @PathParam("idTacheParent") String idTacheParent,
            @PathParam("cip") String cip
    )

    {
        if (idTacheParent.equals("null")){tacheMapper.addTachePasDeParent(titre, description, idProjet, cipCreateur);}
        else tacheMapper.addTache(titre, description, idProjet, cipCreateur, Integer.valueOf(idTacheParent));
        return tacheMapper.selectTache(cip, idProjet);

    }

    // Supprimer une tâche
    @GET
    @Path("removetache/{idTache}")
    public String removeTache(@PathParam("idTache") Integer idTache) {
        tacheMapper.removeTache(idTache);
        // Message de confirmation de suppression de la tâche
        return "La tâche ayant l'ID: " + idTache + " a été supprimée.";
    }

    // Modifier une tâche
    @GET
    @Path("modifytache/{idTache}/{titre}/{description}")
    public void modifyTache(
            @PathParam("idTache") Integer idTache,
            @PathParam("titre") String titre,
            @PathParam("description") String description)
    {
        tacheMapper.modifyTache(idTache, titre, description);
    }

    // Afficher une tâche
    @GET
    @Path("printtache/{idTache}")
    public Tache printTache(@PathParam("idTache") Integer idTache)
    {
        return tacheMapper.printTache(idTache);
    }

    // Afficher la table des tâches
    @GET
    @Path("printtabletache")
    public List<Tache> printTableTache()
    {
        return tacheMapper.printTableTache();
    }

    @GET
    @Path("addTacheUser/{cip}/{idTache}/{admin}")
    public void addUser(@PathParam("idTache") Integer idTache, @PathParam("cip") String cip, @PathParam("admin") Boolean admin){
        tacheMapper.AddUser(cip, idTache, admin);
    }

    @GET
    @Path("deleteTacheUser/{cipUsager}/{idTache}")
    public void deleteProjetUser(@PathParam("idTache") Integer idTache, @PathParam("cipUsager")String cipUsager){
        tacheMapper.deleteTacheUser(cipUsager, idTache);
    }

    @GET
    @Path("listTacheUser/{idTache}")
    public List<ParticipatingUser> listProjetUser(@PathParam("idTache") Integer idTache){
        return tacheMapper.listTacheUser(idTache);
    }
}