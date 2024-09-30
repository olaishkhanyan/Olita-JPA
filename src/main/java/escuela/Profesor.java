package escuela;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Table(name = "profesor")
public class Profesor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Setter
        @Getter
        private int ID;
        private String nombre;
        private String apellido;
        private int edad;
        private int anios_experiencia;


    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
        private Set<Curso> cursos;
}
