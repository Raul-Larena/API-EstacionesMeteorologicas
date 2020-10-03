package ezequiel.movasim.repository;

import ezequiel.movasim.model.Station;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station , Integer> {
  List<Station> findByStateTrue();
  List<Station> findByName(String name);
  List<Station> findByCoordinates(int coordinates);
}
