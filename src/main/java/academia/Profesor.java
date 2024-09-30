package academia;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter

public class Profesor extends Personal{


    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
List<Asignatura> asignaturas;
Profesor(){
    this.asignaturas = new ArrayList<>();
}
    }

