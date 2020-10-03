    package ezequiel.movasim.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table()
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    private String username;
    private String password;
   
    
    
    public User() {

    }

}
