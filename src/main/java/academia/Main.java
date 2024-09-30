package academia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Boolean seguirInsertando = true;

        while (seguirInsertando ) {
            System.out.println("Qué operación deseas hacer?");
            System.out.println("1. Insertar un profesor");
            System.out.println("2. Insertar un alumno");
            System.out.println("3. Insertar asignatura");
            System.out.println("4. Vincular profesor a asignatura\n");
            System.out.println("5. Vincular alumno a asignatura\n");
            System.out.println("6. Listar profesores\n");
            System.out.println("7. Listar alumnos  (mostrando sus faltas de asistencia.)\n");
            System.out.println("8. Listar asignaturas\n");
            System.out.println("9. Listar asignaturas de un profesor\n");
            System.out.println("10. Listar asignaturas de un alumno\n");
            System.out.println("11. Eliminar profesor\n");
            System.out.println("12. Eliminar alumno\n");
            System.out.println("13. Eliminar asignatura\n");
            System.out.println("14. Modificar profesor\n");
            System.out.println("15. Modificar alumno\n");
            System.out.println("16. Modificar asignatura\n");
            System.out.println("17. Salir de la aplicación\n");
            String respuestaUsuario = br.readLine();
            switch (respuestaUsuario) {
                case "1":
                    insertarProfesor(em, br);
                    break;
                case "2":
                    insertarAlumno(em, br);
                    break;
                case "3":
                    insertarAsignatura(em, br);
                    break;
                case "4":
                    vincularProfesorAAsignatura(em, br);
                    break;
                case "5":
                    vincularAlumnoAAsignatura(em, br);
                    break;
                case "6":
                    listarProfesores(em);
                    break;
                case "7":
                    listarAlumnos(em);
                    break;
                case "8":
                    listarAsignaturas(em);
                    break;
                case "9":
                    listarAsignaturasDelProfe(em, br);
                    break;
                case "10":
                    listarAsignaturasDelAlumno(em, br);
                    break;
                case "11":
                    eliminarProfesor(em, br);
                    break;
                case "12":
                    eliminarAlumno(em, br);
                    break;
                case "13":
                    eliminarAsignatura(em, br);
                    break;
                case "14":
                    modificarProfesor(em, br);
                    break;
                case "15":
                    modificarAlumno(em, br);
                    break;
                case "16":
                    modificarAsignatura(em, br);
                    break;
                case "17":
                    seguirInsertando = false;
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
        em.close();
    }

    public static void insertarProfesor(EntityManager em, BufferedReader br) throws Exception {
        System.out.println("Introduce el nombre del profesor:");
        String nombre = br.readLine();

        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(profesor);
        tx.commit();

        System.out.println("Profesor insertado con éxito.");
    }

    public static void insertarAlumno(EntityManager em, BufferedReader br) throws Exception {
        System.out.println("Introduce el nombre del alumno:");
        String nombre = br.readLine();
        System.out.println("Introduce las faltas de asistencia del alumno:");
        String faltas = br.readLine();

        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setFaltasDeAsistencia(faltas);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(alumno);
        tx.commit();

        System.out.println("Alumno insertado con éxito.");
    }

    public static void insertarAsignatura(EntityManager em, BufferedReader br) throws Exception {
        System.out.println("Introduce el nombre de la asignatura:");
        String nombre = br.readLine();

        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(nombre);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(asignatura);
        tx.commit();

        System.out.println("Asignatura insertada con éxito.");
    }

    public static void vincularAlumnoAAsignatura(EntityManager em, BufferedReader br) throws Exception {
        System.out.println("Introduce el id del alumno que quieres vincular a la asignatura:");
        Long idAlumno = Long.parseLong(br.readLine());

        System.out.println("Introduce el id de la asignatura que quieres asignar:");
        Long idAsignatura = Long.parseLong(br.readLine());

        Alumno alumno = em.find(Alumno.class, idAlumno);

        Asignatura asignatura = em.find(Asignatura.class, idAsignatura);
        alumno.getAsignaturas().add(asignatura);

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(alumno);
            tx.commit();
            System.out.println("Alumno " + idAlumno + " vinculado a la asignatura " + idAsignatura + " con éxito.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al vincular el profesor a la asignatura: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void vincularProfesorAAsignatura(EntityManager em, BufferedReader br) throws Exception {
        System.out.println("Introduce el id del profesor que quieres vincular a la asignatura:");
        Long idProfesor = Long.parseLong(br.readLine());

        System.out.println("Introduce el id de la asignatura que quieres asignar:");
        Long idAsignatura = Long.parseLong(br.readLine());

        Profesor profesor = em.find(Profesor.class, idProfesor);

        Asignatura asignatura = em.find(Asignatura.class, idAsignatura);
//        if(profesor.getAsignaturas() == null){
//            profesor.setAsignaturas(new ArrayList<>());
//        }
        profesor.getAsignaturas().add(asignatura);

        asignatura.setProfesor(profesor);

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(profesor);
            tx.commit();
            System.out.println("Profesor " + idProfesor + " vinculado a la asignatura " + idAsignatura + " con éxito.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al vincular el profesor a la asignatura: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void listarProfesores(EntityManager em) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Profesor> query = em.createQuery("SELECT p from Profesor p", Profesor.class);
        List<Profesor> result = query.getResultList();
        tx.commit();

        System.out.println("Esa es la lista entera de los profesores");
        for (Profesor profesor : result) {
            System.out.println(profesor.getNombre());
        }
    }

    public static void listarAlumnos(EntityManager em) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Alumno> query = em.createQuery("SELECT a from Alumno a", Alumno.class);
        List<Alumno> result = query.getResultList();
        tx.commit();

        System.out.println("Esa es la lista entera de los alumnos");
        for (Alumno alumno : result) {
            System.out.println(alumno.getNombre() + " " + alumno.getFaltasDeAsistencia() + "faltas");
        }
    }

    public static void listarAsignaturas(EntityManager em) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Asignatura> query = em.createQuery("SELECT a from Asignatura a", Asignatura.class);
        List<Asignatura> result = query.getResultList();
        tx.commit();

        System.out.println("Esa es la lista entera de los alumnos");
        for (Asignatura asignatura : result) {
            System.out.println(asignatura.getNombre());
        }
    }
    public static void listarAsignaturasDelProfe(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el id del profesor");
        Long idProfesor = Long.parseLong(br.readLine());

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Asignatura> query = em.createQuery("SELECT a from Asignatura a WHERE profesor_id = " + idProfesor, Asignatura.class);
        List<Asignatura> result = query.getResultList();
        tx.commit();


        System.out.println("Esa es la lista de lo asignatura del profe");
        for (Asignatura asignatura : result) {
            System.out.println(asignatura.getNombre());
        }
    }
    public static void listarAsignaturasDelAlumno(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el id del alumno");
        Long idAlumno = Long.parseLong(br.readLine());

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        TypedQuery<Asignatura> query = em.createQuery("SELECT a from Asignatura a JOIN a.alumnos al WHERE al.id = " + idAlumno, Asignatura.class);

        List<Asignatura> result = query.getResultList();
        tx.commit();


        System.out.println("Esa es la lista de lo asignatura del alumno");
        for (Asignatura asignatura : result) {
            System.out.println(asignatura.getNombre());
        }
    }
    public static void eliminarProfesor(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el nombre del profesor que quieres eliminar");
        Long id = Long.parseLong(br.readLine());

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Profesor profesor = em.find(Profesor.class, id);
        em.remove(profesor);
        em.getTransaction().commit();

        System.out.println("El" + id + "profesor ha sido eliminado ");
    }
    public static void eliminarAlumno(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el nombre del alumno que quieres eliminar");
        Long id = Long.parseLong(br.readLine());

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Alumno alumno = em.find(Alumno.class, id);
        em.remove(alumno);
        em.getTransaction().commit();

        System.out.println("El" + id + "alumno ha sido eliminado ");
    }
    public static void eliminarAsignatura(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el nombre de la Asignatura que quieres eliminar");
        Long id = Long.parseLong(br.readLine());

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Asignatura asignatura= em.find(Asignatura.class, id);
        em.remove(asignatura);
        em.getTransaction().commit();

        System.out.println("El" + id + "asignatura ha sido eliminado ");
    }
    public static void modificarProfesor(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el nombre del Profesor que quieres modificar");
        String nombreActual = br.readLine();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Profesor> query = em.createQuery("SELECT p FROM Profesor p WHERE p.nombre = :nombre", Profesor.class);
        query.setParameter("nombre", nombreActual);
        List<Profesor> profesores = query.getResultList();

        Profesor profesor = profesores.get(0);

        System.out.println("Introduce el nuevo nombre del Profesor:");
        String nuevoNombre = br.readLine();

        profesor.setNombre(nuevoNombre);
        em.merge(profesor);
        em.getTransaction().commit();

        System.out.println("El " + nuevoNombre + "es el nuevo nombre ");
    }
    public static void modificarAlumno(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el nombre del Alumno que quieres modificar");
        String nombreActual = br.readLine();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a WHERE a.nombre = :nombre", Alumno.class);
        query.setParameter("nombre", nombreActual);
        List<Alumno> alumnos = query.getResultList();

        Alumno alumno= alumnos.get(0);

        System.out.println("Introduce el nuevo nombre del Profesor:");
        String nuevoNombre = br.readLine();

        alumno.setNombre(nuevoNombre);
        em.merge(alumno);
        em.getTransaction().commit();

        System.out.println("El " + nuevoNombre + "es el nuevo nombre ");
    }
    public static void modificarAsignatura(EntityManager em, BufferedReader br) throws IOException {
        System.out.println("Introduce el nombre del Asignatura que quieres modificar");
        String nombreActual = br.readLine();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        TypedQuery<Asignatura> query = em.createQuery("SELECT a FROM Asignatura a WHERE a.nombre = :nombre", Asignatura.class);
        query.setParameter("nombre", nombreActual);
        List<Asignatura> asignaturas = query.getResultList();

        Asignatura asignatura = asignaturas.get(0);

        System.out.println("Introduce el nuevo nombre del Profesor:");
        String nuevoNombre = br.readLine();

        asignatura.setNombre(nuevoNombre);
        em.merge(asignatura);
        em.getTransaction().commit();

        System.out.println("El " + nuevoNombre + "es el nuevo nombre ");
    }
}


