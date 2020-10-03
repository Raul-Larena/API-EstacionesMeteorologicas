package ezequiel.movasim.service;

import ezequiel.movasim.model.Report;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ezequiel.movasim.repository.ReportRepository;

@Service
public class ReportService {
    
    @Autowired
    private ReportRepository reportRepository;
    
    
    public Report create(Report report){
        return reportRepository.save(report);
    }
    
    public List<Report> toList(){
        return reportRepository.findAll();
    }
    
    public Optional<Report> returnById(int id){
        return reportRepository.findById(id);
    }
    
    public Report update(Report report){
        return reportRepository.save(report);
    }
    
    public void delete(int id){
        reportRepository.deleteById(id);
    }
    
    
}
