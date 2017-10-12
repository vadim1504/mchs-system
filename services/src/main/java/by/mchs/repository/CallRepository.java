package by.mchs.repository;

import by.mchs.model.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallRepository extends JpaRepository<Call,Integer> {

    @Query("select case when count (u)>0 then 'true' else 'false' end from Call u where u.idCall =?1 and u.idTower =?2")
    boolean existsByIdCall(int idCall,int idTower);

    @Query("SELECT u FROM Call u WHERE u.idCall IN (SELECT u.idCall FROM Call GROUP BY u.idCall HAVING COUNT( u.idCall ) > 2 )")
    List<Call> getAllCallByOneUser();

}

