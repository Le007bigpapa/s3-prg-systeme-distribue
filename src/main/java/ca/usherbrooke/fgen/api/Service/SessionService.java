package ca.usherbrooke.fgen.api.Service;

import ca.usherbrooke.fgen.api.business.Session;
import ca.usherbrooke.fgen.api.mapper.SessionMapper;
import org.eclipse.microprofile.jwt.JsonWebToken;
/*import org.apache.ibatis.annotations.Param;
import org.jsoup.parser.Parser;*/

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/*import java.util.stream.Collectors;*/
import javax.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionService {

    @Inject
    SessionMapper sessionMapper;


    @GET
    @Path("getsessionsofcip/{cip}/{idTache}")




    public List<Session> getSessionsCip(
            @PathParam("cip") String cipUsager,
            @PathParam("idTache") Integer idTache
    ) {
        return sessionMapper.selectSessionOfCip(cipUsager, idTache);

    }

    @GET
    @Path("getsessions/{idTache}")




    public List<Session> getSessions(
            @PathParam("idTache") Integer idTache
    ) {
        return sessionMapper.selectSession(idTache);

    }
    

    @GET
    @Path("chrono/{cip}/{idTache}")

    

    public List<Session> startChrono(
            //public void startChrono(
            @PathParam("cip") String cipUsager,
            @PathParam("idTache") Integer idTache
    ) {
        try {
            //Si la dernière session pour la tâche n'est pas compléter. Il va la compléter. Sinon il en crée une nouvelle.
            if (!sessionMapper.isLastSessionUnfinished(cipUsager, idTache)) {
                boolean result= sessionMapper.startSession(cipUsager, idTache);
                if (result){
                    return sessionMapper.selectSessionOfCip(cipUsager, idTache);
                }  else {
                    throw new WebApplicationException(Response.Status.METHOD_NOT_ALLOWED);
                }
            } else {
                boolean result= sessionMapper.stopSession(cipUsager, idTache);
                if (result){
                    return sessionMapper.selectSessionOfCip(cipUsager, idTache);

                }  else {
                    throw new WebApplicationException(Response.Status.METHOD_NOT_ALLOWED);
                }
            }
        } catch (Exception e) {
            throw new WebApplicationException("Failed to start/stop session", e);
        }
    }

}