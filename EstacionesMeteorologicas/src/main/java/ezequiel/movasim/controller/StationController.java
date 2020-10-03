package ezequiel.movasim.controller;

import ezequiel.movasim.model.Station;
import ezequiel.movasim.service.StationService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("station")
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping
    public Station create(@RequestParam String name,
            @RequestParam int coordinates,
            @RequestParam boolean solarPanel) {
        return stationService.create(name,coordinates, solarPanel);
    }

    @GetMapping
    public List<Station> toList() {
        return stationService.toList();
    }

    @GetMapping("{id}")
    public Optional<Station> returnById(@PathVariable int id) {
        return stationService.returnById(id);
    }

    @GetMapping("/name")
    public List<Station> returnByName(@RequestParam String name) {
        return stationService.returnByName(name);
    }

    @GetMapping("/coordinates")
    public List<Station> returnByCoordinates(@RequestParam int coordinates) {
        return stationService.returnByCoordinates(coordinates);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Station> station = stationService.returnById(id);
        try {
            stationService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
