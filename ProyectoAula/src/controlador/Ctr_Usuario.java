package controlador;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Usuario;

public class Ctr_Usuario {

    // Instancia única (Singleton)
    private static Ctr_Usuario instancia;
    private ArrayList<Usuario> listaUsuarios;

    // Constructor privado para evitar instancias externas
    private Ctr_Usuario() {
        listaUsuarios = new ArrayList<>();
        cargarUsuariosDePrueba();
    }

    // Método para obtener la única instancia
    public static Ctr_Usuario getInstance() {
        if (instancia == null) {
            instancia = new Ctr_Usuario();
        }
        return instancia;
    }

    public boolean validarLogin(String usuario, String contraseña) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equals(usuario) && u.getContraseña().equals(contraseña)) {
                if (u.getEstado() == 1) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + u.getNombre());
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario está inactivo.");
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
        return false;
    }

    private void cargarUsuariosDePrueba() {
        Usuario u1 = new Usuario();
        u1.setIdUsuario(1);
        u1.setNombre("Alvaro");
        u1.setApellido("Muñoz");
        u1.setUsuario("admin");
        u1.setContraseña("1234567");
        u1.setTelefono("123456789");
        u1.setEstado(1);
        listaUsuarios.add(u1);

        Usuario u2 = new Usuario();
        u2.setIdUsuario(2);
        u2.setNombre("Victor");
        u2.setApellido("Mena");
        u2.setUsuario("victor");
        u2.setContraseña("12345678");
        u2.setTelefono("987654321");
        u2.setEstado(0);
        listaUsuarios.add(u2);
    }

    public boolean registrarNuevoUsuario(Usuario nuevo) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equalsIgnoreCase(nuevo.getUsuario())) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya está en uso.");
                return false;
            }
        }
        nuevo.setIdUsuario(listaUsuarios.size() + 1);
        nuevo.setEstado(1);
        listaUsuarios.add(nuevo);
        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
        return true;
    }

    public void mostrarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : listaUsuarios) {
                System.out.println("ID: " + u.getIdUsuario() + ", Usuario: " + u.getUsuario() + ", Estado: " + (u.getEstado() == 1 ? "Activo" : "Inactivo"));
            }
        }
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
