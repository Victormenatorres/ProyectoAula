package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Usuario;

public class Ctrl_Usuario {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static int ultimoId = 0;

    /**
     * Método para guardar un nuevo usuario
     */
    public boolean guardar(Usuario objeto) {
        try {
            // Verificar si el usuario ya existe
            if (existeUsuario(objeto.getUsuario())) {
                JOptionPane.showMessageDialog(null, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Asignar ID autoincremental
            objeto.setIdUsuario(++ultimoId);
            objeto.setEstado(1); // Estado activo por defecto
            
            // Agregar a la lista
            usuarios.add(objeto);
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Método para consultar si un usuario ya existe
     */
    public boolean existeUsuario(String usuario) {
        for (Usuario user : usuarios) {
            if (user.getUsuario().equalsIgnoreCase(usuario)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para autenticar usuario (login)
     */
    public boolean loginUser(String usuario, String password) {
    for (Usuario user : usuarios) {
        if (user.getUsuario().equals(usuario) && 
            user.getContraseña().equals(password) && // Ahora getPassword() existe
            user.getEstado() == 1) {
            return true;
        }
    }
    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de login", JOptionPane.ERROR_MESSAGE);
    return false;
}
    
    /**
     * Método para actualizar un usuario existente
     */
    public boolean actualizar(Usuario objeto, int idUsuario) {
        try {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getIdUsuario() == idUsuario) {
                    // Mantener el mismo ID y estado
                    objeto.setIdUsuario(idUsuario);
                    objeto.setEstado(usuarios.get(i).getEstado());
                    
                    // Actualizar el usuario en la lista
                    usuarios.set(i, objeto);
                    JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
                    return true;
                }
            }
            JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Método para "eliminar" un usuario (cambiar estado a inactivo)
     */
    public boolean eliminar(int idUsuario) {
        for (Usuario user : usuarios) {
            if (user.getIdUsuario() == idUsuario) {
                user.setEstado(0); // 0 = Inactivo
                JOptionPane.showMessageDialog(null, "Usuario desactivado correctamente");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    /**
     * Método para obtener todos los usuarios activos
     */
    public List<Usuario> obtenerUsuariosActivos() {
        List<Usuario> activos = new ArrayList<>();
        for (Usuario user : usuarios) {
            if (user.getEstado() == 1) {
                activos.add(user);
            }
        }
        return activos;
    }

    /**
     * Método para buscar usuario por ID
     */
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        for (Usuario user : usuarios) {
            if (user.getIdUsuario() == idUsuario) {
                return user;
            }
        }
        return null;
    }

    /**
     * Método para inicializar datos de prueba (opcional)
     */
    public static void inicializarDatosPrueba() {
        usuarios.add(new Usuario(++ultimoId, "Admin", "Sistema", "admin", "admin123", "0999999999", 1));
        usuarios.add(new Usuario(++ultimoId, "Juan", "Pérez", "juan", "juan123", "0988888888", 1));
    }
}