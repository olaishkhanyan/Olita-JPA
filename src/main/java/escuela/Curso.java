package escuela;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

    @Table(name = "curso")
    public class Curso {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        // Getters and Setters
        @Setter
        @Getter
        private int ID;
        private String nombre;
        private Date fecha_inico;
        private int precio;
        private int id_profesor;

        @ManyToOne
        @JoinColumn(name = "profesor_id")
        private Profesor profesor;
    }