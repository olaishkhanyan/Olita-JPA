package academia;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
@Setter
@Getter

    public class Asignatura {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;


    @ManyToMany (mappedBy = "asignaturas")
    List<Alumno> alumnos;

    @ManyToOne
    private Profesor profesor;

}
