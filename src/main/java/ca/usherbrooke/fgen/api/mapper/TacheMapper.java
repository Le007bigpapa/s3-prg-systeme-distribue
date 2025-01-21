package ca.usherbrooke.fgen.api.mapper;
import ca.usherbrooke.fgen.api.business.ParticipatingUser;
import ca.usherbrooke.fgen.api.business.Tache;
import org.apache.ibatis.annotations.*;

import javax.ws.rs.PathParam;
import java.util.List;

@Mapper
public interface TacheMapper {

    // Sélectionner une tâche
    List<Tache> selectTache(@Param("cipUsager") String cipUsager, @Param("idProjet") Integer idProjet);

    // Ajouter une tâche
    void addTache(@Param("titre") String titre,
                  @Param("description") String description,
                  @Param("idProjet") Integer idProjet,
                  @Param("cipCreateur") String cipCreateur,
                  @Param("idTacheParent") Integer idTacheParent);

    void addTachePasDeParent(@Param("titre") String titre,
                             @Param("description") String description,
                             @Param("idProjet") Integer idProjet,
                             @Param("cipCreateur") String cipCreateur);

    // Supprimer une tâche
    void removeTache(@Param("idTache") Integer idTache);

    // Modifier une tâche
    void modifyTache(@Param("idTache") Integer idTache,
                     @Param("titre") String titre,
                     @Param("description") String description);

    // Afficher une tâche
    Tache printTache(@Param("idTache") Integer idTache);

    // Afficher la table des tâches
    List<Tache> printTableTache();


    void AddUser(@Param("cip") String cip,
                 @Param("idTache") Integer idTache,
                 @Param("admin") Boolean admin
    );
    void deleteTacheUser(@Param("cip") String cip, @Param("id") Integer idTache);

    List<ParticipatingUser> listTacheUser(@Param("idTache") Integer idTache);

}