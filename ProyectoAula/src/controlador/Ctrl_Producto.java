package controlador;


import modelo.Producto;
import java.util.ArrayList;
import java.util.List;


public class Ctrl_Producto {

    // Lista para almacenar los productos
  private static List<Producto> productos = new ArrayList<>();

public boolean guardar(Producto producto) {
        if (!existeProducto(producto.getNombre())) {
            producto.setIdProducto(productos.size() + 1);
            producto.setEstado(1); // 1 = Activo
            productos.add(producto);
            return true;
        }
        return false;
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
    
    // metodo para editar el producto 
    public boolean editarProducto(Producto nuevoProducto) {
    for (int i = 0; i < productos.size(); i++) {
        if (productos.get(i).getIdProducto() == nuevoProducto.getIdProducto()) {
            productos.set(i, nuevoProducto);
            return true;
        }
    }
    return false;
    
    
}
    // metodo para actualizar 
    public boolean actualizar(Producto objeto, int idProducto) {
    boolean respuesta = false;

    for (Producto p : productos) {
        if (p.getIdProducto() == idProducto) {
            // Actualizar los campos del producto encontrado
            p.setNombre(objeto.getNombre());
            p.setCantidad(objeto.getCantidad());
            p.setPrecio(objeto.getPrecio());
            p.setDescripcion(objeto.getDescripcion());
            p.setPorcentajeIva(objeto.getPorcentajeIva());
            p.setIdCategoria(objeto.getIdCategoria());
            p.setEstado(objeto.getEstado());
            respuesta = true;
            break;
        }
    }

    return respuesta;
}
    
    // metodo eliminar producto 
    public boolean eliminar(int idProducto) {
    boolean respuesta = false;

    for (int i = 0; i < productos.size(); i++) {
        if (productos.get(i).getIdProducto() == idProducto) {
            productos.remove(i);
            respuesta = true;
            break;
        }
    }

    return respuesta;
} 

public Producto obtenerProductoPorId(int idProducto) {
    for (Producto producto : productos) {
        if (producto.getIdProducto() == idProducto && producto.getEstado() == 1) {
            return producto; // Devuelve el producto si coincide el ID y está activo
        }
    }
    return null; // Retorna null si no encuentra el producto
}
  public List<Producto> obtenerProductosActivos() {
        // Versión tradicional con for-loop
        List<Producto> activos = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getEstado() == 1) {  // 1 = Activo
                activos.add(p);
            }
        }
        return activos;
        
        
    }



}
