package ezequiel.movasim.controller;

import ezequiel.movasim.model.Station;
import ezequiel.movasim.service.StationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("abm")
public class AbmController {

    @Autowired
    private StationService stationService;

    @PutMapping("/alta")
    public ResponseEntity high(@RequestParam int id) {
        try {
            Optional<Station> station = stationService.returnById(id);
            station.get().setState(true);
            stationService.update(station.get());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/alta/name")
    public ResponseEntity highName(@RequestParam String name) {
        List<Station> stations = stationService.returnByName(name);

        if (stations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            for (Station station : stations) {
                station.setState(true);
                stationService.update(station);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/alta/coordinates")
    public ResponseEntity highCoordinates(@RequestParam int coordinates) {
        List<Station> stations = stationService.returnByCoordinates(coordinates);

        if (stations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            for (Station station : stations) {
                station.setState(true);
                stationService.update(station);
            }

            return ResponseEntity.status(HttpStatus.OK).build();
        }

    }

    @PutMapping("/baja")
    public ResponseEntity low(@RequestParam int id) {
        try {
            Optional<Station> station = stationService.returnById(id);
            station.get().setState(false);
            stationService.update(station.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/baja/name")
    public ResponseEntity lowName(@RequestParam String name) {
        List<Station> stations = stationService.returnByName(name);
        if (stations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            for (Station station : stations) {
                station.setState(false);
                stationService.update(station);
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }

    }

    @PutMapping("/baja/coordinates")
    public ResponseEntity lowCoordinates(@RequestParam int coordinates) {

        List<Station> stations = stationService.returnByCoordinates(coordinates);

        if (stations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            for (Station station : stations) {
                station.setState(false);
                stationService.update(station);
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PutMapping("{modificar}")
    public ResponseEntity modify(@RequestBody Station change) {

        if (stationService.returnById(change.getId()).isEmpty() 
                || !stationService.returnById(change.getId()).get().isState()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            change.setState(true);
            stationService.update(change);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PutMapping("/modificar/name")
    public ResponseEntity modifyByName(@RequestParam String name, @RequestBody Station change ) {

        if (stationService.returnByName(name).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            List<Station> compare = stationService.returnByName(name);
            for (Station compare2 : compare) {

                if (compare2.isState()) {
                    change.setId(compare2.getId());
                    change.setState(true);
                    stationService.update(change);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PutMapping("/modificar/coordinates")
    public ResponseEntity modifyByCoordinates(@RequestParam int coordinates, @RequestBody Station change) {

        List<Station> stations = stationService.returnByCoordinates(coordinates);

        if (stations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            for (Station station : stations) {
                change.setId(station.getId());
                change.setState(true);
                stationService.update(change);
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

}
