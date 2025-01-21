package ca.usherbrooke.fgen.api.Service;

import ca.usherbrooke.fgen.api.business.ParticipatingUser;
import ca.usherbrooke.fgen.api.business.Projet;
import ca.usherbrooke.fgen.api.mapper.ProjetMapper;
import com.fasterxml.jackson.dataformat.yaml.util.StringQuotingChecker;
import org.apache.ibatis.annotations.Param;
/*import org.apache.ibatis.annotations.Param;
import org.jsoup.parser.Parser;*/

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/*import java.util.stream.Collectors;*/
import javax.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProjetService {
    @Inject
    ProjetMapper projetMapper;

    @GET
    @Path("selectProjet/{idProjet}")

    public Projet selectProjet(@PathParam("idProjet") Integer idProjet){
        return projetMapper.selectProjet(idProjet);
    }

    @GET
    @Path("newProjet/{cipCreateur}/{titre}/{description}")

    public void newProjet(@PathParam("cipCreateur") String cipCreateur,
                      @PathParam("titre") String titre,
                      @PathParam("description") String description) {

       projetMapper.newProjet(cipCreateur, titre, description);
    }

    @GET
    @Path("editProjet/{idProjet}/{titre}/{description}/{cipDerniereModif}/{cipProprietaire}")

    public void editProjet(@PathParam("idProjet") Integer idProjet,
                       @PathParam("titre") String titre,
                       @PathParam("description") String description,
                       @PathParam("cipDerniereModif") String cipDerniereModification,
                       @PathParam("cipProprietaire") String cipProprietaire){
        projetMapper.editProjet(idProjet, titre, description, cipDerniereModification, cipProprietaire);
    }

    @GET
    @Path("printListProjet/{cipProprietaire}")

    public List<Projet> printListProjet(@PathParam("cipProprietaire") String cipProprietaire){
        return projetMapper.printListProjet(cipProprietaire);
    }

    @GET
    @Path("deleteProjet/{idProjet}")
    public void deleteProjet(@PathParam("idProjet") Integer idprojet){
        projetMapper.deleteProjet(idprojet);
    }


    @GET
    @Path("addProjetUser/{cip}/{idProjet}/{admin}")
    public void addUser(@PathParam("idProjet") Integer idprojet, @PathParam("cip") String cip, @PathParam("admin") Boolean admin){
        projetMapper.AddUser(cip, idprojet, admin);
    }

    @GET
    @Path("deleteProjectUser/{cipUsager}/{idProjet}")
    public void deleteProjetUser(@PathParam("idProjet") Integer idprojet, @PathParam("cipUsager")String cipUsager){
        projetMapper.deleteProjetUser(cipUsager, idprojet);
    }

    @GET
    @Path("listProjectUser/{idProjet}")
    public List<ParticipatingUser> listProjetUser(@PathParam("idProjet") Integer idProjet){
        return projetMapper.listProjetUser(idProjet);
    }
}
