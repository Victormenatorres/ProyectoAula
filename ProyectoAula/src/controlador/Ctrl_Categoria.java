package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

public class Ctrl_Categoria {

    // Simulando una base de datos con una lista
    public static List<Categoria> listaCategorias = new ArrayList<>();

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
}
