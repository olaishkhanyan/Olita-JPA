package escuela;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<String> nombres = session.createQuery("select p.nombre from Profesor p", String.class).getResultList();
            nombres.forEach(nombre -> System.out.println("Nombre: " + nombre));

            List<NombreApellido> apellido  = session.createQuery("SELECT new escuela.NombreApellido(p.nombre, p.apellido) FROM Profesor p ORDER BY p.edad", NombreApellido.class).getResultList();
            apellido.forEach(nombre -> System.out.println("Nombre y Appellido ordenado por el appellido: " + nombre.getNombre() + nombre.getApellidao()));
            System.out.println("==================================");
            List<String> nombres2 = session.createQuery("SELECT p.nombre FROM Profesor p WHERE p.edad >30 AND p.anios_experiencia > 5", String.class).getResultList();
            nombres2.forEach(nombre -> System.out.println("Nombre: " + nombre));
        }
    }
}