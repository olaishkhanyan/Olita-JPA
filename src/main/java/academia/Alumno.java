package academia;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
    @Setter
    @Getter
    public class Alumno extends Personal{


        private String nombre;
        private String faltasDeAsistencia;


        @ManyToMany
        List<Asignatura> asignaturas;
}
