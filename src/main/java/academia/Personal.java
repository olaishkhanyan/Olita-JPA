package academia;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity @Getter @Setter
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;




}
