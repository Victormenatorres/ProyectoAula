package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

public class Ctrl_Categoria {
 
    // Simulando una base de datos con una lista
    public static List<Categoria> listaCategorias = new ArrayList<>();
       static {
        // Datos iniciales de ejemplo - reemplaza con tus categorías reales
       listaCategorias.add(new Categoria(1, "Electrónicos", 1));
        listaCategorias.add(new Categoria(2, "Alimentos", 1));
        listaCategorias.add(new Categoria(3, "Bebidas", 1));
    }
    
    public List<Categoria> obtenerCategoriasActivas() {
        List<Categoria> activas = new ArrayList<>();
        for(Categoria cat : listaCategorias) {
            if(cat.getEstado() == 1) {
                activas.add(cat);
            }
        }
        return activas;
    }

    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        try {
            if (!existeCategoria(objeto.getDescripcion())) {
                int nuevoId = listaCategorias.size() + 1; // ID basado en posición
                objeto.setIdCategoria(nuevoId); // Asignamos el ID
                listaCategorias.add(objeto);
                respuesta = true;
            } else {
                System.out.println("La categoría '" + objeto.getDescripcion() + "' ya está registrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la categoría: " + e.getMessage());
        }
        return respuesta;
    }

// Verificar si ya existe una categoría con el mismo id
    public boolean existeCategoria(String descripcion) {
        for (Categoria c : listaCategorias) {
            if (c.getDescripcion().equalsIgnoreCase(descripcion)) {
                return true;
            }
        }
        return false;
    }

// Obtener todas las categorías
    public List<Categoria> obtenerCategorias() {
        return listaCategorias;
    }

// Eliminar una categoría por id
    public boolean eliminarCategoria(int idCategoria) {
        for (Categoria c : listaCategorias) {
            if (c.getIdCategoria() == idCategoria) {
                return listaCategorias.remove(c);
            }
        }
        return false;
    }

// Actualizar una categoría existente (por id)
    public boolean editarCategoria(Categoria nuevaCategoria) {
        for (int i = 0; i < listaCategorias.size(); i++) {
            if (listaCategorias.get(i).getIdCategoria() == nuevaCategoria.getIdCategoria()) {
                listaCategorias.set(i, nuevaCategoria);
                return true;
            }
        }
        return false;
    }
    // Obtener ID de categoría por descripción


public int obtenerIdCategoriaPorNombre(String nombreCategoria) {
    for (Categoria cat : listaCategorias) {
        if (cat.getDescripcion().equalsIgnoreCase(nombreCategoria)) {
            return cat.getIdCategoria();
        }
    }
    return -1; // No encontrada
}


  // Método para obtener ID por nombre
    public int obtenerIdPorDescripcion(String descripcion) {
        for (Categoria cat : listaCategorias) {
            if (cat.getDescripcion().equalsIgnoreCase(descripcion)) {
                return cat.getIdCategoria();
            }
        }
        return -1; // Retorna -1 si no encuentra
    }
    
    // Método para obtener nombre por ID
    public String obtenerNombreCategoriaPorId(int idCategoria) {
        for (Categoria cat : listaCategorias) {
            if (cat.getIdCategoria() == idCategoria) {
                return cat.getDescripcion();
            }
        }
        return "Sin categoría";
    }


}
