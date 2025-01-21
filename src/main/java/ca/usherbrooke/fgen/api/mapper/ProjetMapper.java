package ca.usherbrooke.fgen.api.mapper;


import ca.usherbrooke.fgen.api.business.ParticipatingUser;
import ca.usherbrooke.fgen.api.business.Projet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.ws.rs.PathParam;
import java.util.List;



@Mapper
public interface ProjetMapper {

    Projet selectProjet(@Param("idProjet") Integer idprojet);

    void newProjet(@Param("cipCreateur") String cipCreateur,
                          @Param("titre") String titre,
                          @Param("description") String description);

    void editProjet(@Param("idProjet") Integer idProjet,
                       @Param("titre") String titre,
                       @Param("description") String description,
                       @Param("cipDerniereModif") String cipDerniereModification,
                       @Param("cipProprietaire") String cipProprietaire);

    List<Projet> printListProjet(@Param("cipProprietaire") String cipProprietaire); //Print tous les projets d'un usagers


    void deleteProjet(@Param("idProjet") Integer idprojet);

    void AddUser(@Param("cip") String cip,
                 @Param("idProjet") Integer idprojet,
                 @Param("admin") Boolean admin
    );
    void deleteProjetUser(@Param("cip") String cip, @Param("id") Integer idProjet);

    List<ParticipatingUser> listProjetUser(@Param("idProjet") Integer idProjet);
}
