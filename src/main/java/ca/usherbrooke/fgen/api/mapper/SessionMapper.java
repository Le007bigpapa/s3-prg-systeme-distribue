package ca.usherbrooke.fgen.api.mapper;


import ca.usherbrooke.fgen.api.business.Session;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Mapper
public interface SessionMapper {

    List<Session> selectSession(@Param("idTache") Integer idTache);

    List<Session> selectSessionOfCip(@Param("cipUsager") String cipUsager,
                         @Param("idTache") Integer idTache);

    boolean startSession(@Param("cipUsager") String cipUsager,
                         @Param("idTache") Integer idTache);

    boolean stopSession(@Param("cipUsager") String cipUsager,
                              @Param("idTache") Integer idTache);

    boolean isLastSessionUnfinished(@Param("cipUsager") String cipUsager,
                              @Param("idTache") Integer idTache);
}
