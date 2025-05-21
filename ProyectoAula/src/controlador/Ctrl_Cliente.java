package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Producto;

public class Ctrl_Cliente {
    private static List<Cliente> clientes = new ArrayList<>();
    private static int ultimoId = 0;
    private static List<Producto> productos = new ArrayList<>();

    /**
     * Método para guardar un nuevo cliente
     */
    public boolean guardar(Cliente objeto) {
        try {
            // Verificar si el cliente ya existe
            if (existeCliente(objeto.getCedula())) {
                return false;
            }
            
            // Asignar ID autoincremental
            objeto.setIdCliente(++ultimoId);
            
            // Agregar a la lista
            clientes.add(objeto);
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para consultar si un cliente ya existe por cédula
     */
    public boolean existeCliente(String cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equalsIgnoreCase(cedula)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para actualizar un cliente existente
     */
    public boolean actualizar(Cliente objeto, int idCliente) {
        try {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getIdCliente() == idCliente) {
                    // Mantener el mismo ID y estado
                    objeto.setIdCliente(idCliente);
                    objeto.setEstado(clientes.get(i).getEstado());
                    
                    // Actualizar el cliente en la lista
                    clientes.set(i, objeto);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para "eliminar" un cliente (cambiar estado a inactivo)
     */
    public boolean eliminar(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                cliente.setEstado(0); // 0 = Inactivo
                return true;
            }
        }
        return false;
    }

    /**
     * Método para obtener todos los clientes activos
     */
    public List<Cliente> obtenerClientesActivos() {
        List<Cliente> activos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getEstado() == 1) { // 1 = Activo
                activos.add(cliente);
            }
        }
        return activos;
    }

    /**
     * Método para buscar cliente por ID
     */
    public Cliente obtenerClientePorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Método para inicializar datos de prueba (opcional)
     */
    public static void inicializarDatosPrueba() {
        clientes.add(new Cliente(++ultimoId, "Juan", "Pérez", "123456789", "0991234567", "Av. Principal 123", 1));
        clientes.add(new Cliente(++ultimoId, "María", "Gómez", "987654321", "0987654321", "Calle Secundaria 456", 1));
    }
    public static List<Cliente> obtenerTodosClientes() {
    return new ArrayList<>(clientes);
}
}