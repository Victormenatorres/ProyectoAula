package modelo;

/**
 *
 * @author JOSE
 */
public class Usuario {

    private int idUsuario;
    private String Nombre;
    private String Apellido;
    private String Usuario;
    private String Contraseña;
    private String Telefono;
    private int Estado;

    public Usuario() {
        this.idUsuario = 0;
        this.Nombre = "";
        this.Apellido = "";
        this.Usuario = "";
        this.Contraseña = "";
        this.Telefono = "";
        this.Estado = 0;

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

}
