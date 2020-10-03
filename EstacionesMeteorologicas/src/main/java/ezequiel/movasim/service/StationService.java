package ezequiel.movasim.service;

import ezequiel.movasim.model.Station;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ezequiel.movasim.repository.StationRepository;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public Station create(String name, int coordinates, boolean solarPanel) {
        Station station = new Station(name, coordinates, solarPanel);
        return stationRepository.save(station);
    }

    public List<Station> toList() {
        return stationRepository.findByStateTrue();
    }

    public Optional<Station> returnById(int id) {
        return stationRepository.findById(id);
    }

    public void delete(int id) {
        stationRepository.deleteById(id);
    }

    public Station update(Station newStation) {
        return stationRepository.save(newStation);
    }

    public List<Station> returnByName(String name) {
        return  stationRepository.findByName(name);
    }

    public List<Station> returnByCoordinates(int coordinates) {
        return  stationRepository.findByCoordinates(coordinates);
    }
}
