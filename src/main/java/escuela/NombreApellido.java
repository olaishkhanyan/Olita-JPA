package escuela;

public class NombreApellido {
    private String nombre;

    public NombreApellido(String apellidao, String nombre) {
        this.apellidao = apellidao;
        this.nombre = nombre;
    }

    private String apellidao;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidao() {
        return apellidao;
    }

    public void setApellidao(String apellidao) {
        this.apellidao = apellidao;
    }
}
