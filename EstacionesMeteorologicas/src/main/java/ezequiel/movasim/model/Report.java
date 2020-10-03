package ezequiel.movasim.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table()
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Station station;
    
    private byte humidity;
    private byte temperature;
    private float speed;
    private float rainfall;
    private byte battery;
    
    
    public Report(){
        
    }
    

    public Report(Station station, byte humidity, byte temperature, float speed, float rainfall , byte battery) {
        this.station = station;
        this.humidity = humidity;
        this.temperature = temperature;
        this.speed = speed;
        this.rainfall = rainfall;
        this.battery = battery;
    }
    
    
}
