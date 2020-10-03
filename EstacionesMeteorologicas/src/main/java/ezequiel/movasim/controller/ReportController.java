package ezequiel.movasim.controller;

import ezequiel.movasim.service.StationService;
import ezequiel.movasim.service.ReportService;
import ezequiel.movasim.model.Station;
import ezequiel.movasim.model.Report;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private StationService stationService;

    @PostMapping()
    public Report create(@RequestParam int idStation, @RequestBody Report report) {
        try {
            if ((report.getHumidity() < 10) || (report.getHumidity() > 90)
                    || (report.getTemperature() < -10) || (report.getTemperature() > 60)
                    || (report.getSpeed() < 0) || (report.getSpeed() > 50)
                    || (report.getRainfall() < 0) || (report.getRainfall() >= 10)
                    || (report.getBattery() < 0) || (report.getBattery() > 100)) {
                return null;
            } else {

                Optional<Station> station = stationService.returnById(idStation);
                if (station.isPresent()) {
                    if (station.get().isState()) {
                        report.setStation(station.get());
                        return reportService.create(report);
                    }
                }
                return null;

            }
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping()
    public List<Report> toList() {
        return reportService.toList();
    }

    @DeleteMapping()
    public ResponseEntity delete(@RequestParam int id) {

        try {
            Optional<Report> report = reportService.returnById(id);
            if (report.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else{
                report.get().setStation(null);
                reportService.update(report.get());
                 reportService.delete(report.get().getId());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
