package controlador;

import modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class Ctrl_Producto {

    // Lista para almacenar los productos
    private List<Producto> productos = new ArrayList<>();

    
    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        // Agregar el producto a la lista
        if (objeto != null) {
            productos.add(objeto);
            respuesta = true;
        }
        return respuesta;
    }

    
   
   //  Metodo para consultar si el producto ya está registrado en la lista
  

    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        // Buscar en la lista si el producto ya existe
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(producto)) {
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    // Método para obtener la lista completa de productos (si es necesario)
    public List<Producto> obtenerProductos() {
        return productos;
    }
}
