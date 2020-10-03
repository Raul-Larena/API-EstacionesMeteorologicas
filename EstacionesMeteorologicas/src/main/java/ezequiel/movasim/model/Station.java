package ezequiel.movasim.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int coordinates;
    private boolean solarPanel;
    private boolean state;
 
    
    public Station() {

    }

    public Station(String name, int coordinates, boolean solarPanel) {
        this.name = name;
        this.coordinates = coordinates;
        this.solarPanel = solarPanel;
        this.state = true;
    }

}
